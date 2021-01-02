package com.pilotair.clogbook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.pilotair.clogbook.core.entity.Airport;
import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.service.FlightService;
import com.pilotair.clogbook.core.service.UserService;

@SpringBootApplication
public class ClogbookWebappApplication {

	public static void main( String[] args ) {
		Locale.setDefault( Locale.ENGLISH );
		SpringApplication.run( ClogbookWebappApplication.class, args );
	}

	/**
	 * Utilisé pour nullifier les proxys hibernate avant que les entities soient
	 * transmises à Jackson pour conversion en JSon
	 * 
	 * @return une nouvelle instance de hibernate5Module
	 */
	@Bean
	public Hibernate5Module dataTypeHibernateModule() {
		return new Hibernate5Module();
	}

	// Instanciation pour permettre de faire des requetes ajax dans l'appli
	@Bean
	public RestTemplate restTemplate( RestTemplateBuilder builder ) {
		return builder.build();
	}

	@Bean
	@Autowired
	public Object Test( FlightService flightService, UserService userService ) {

		Flight f = createAFlight();
		f.setUserId( userService.getByUserName( "colin.lefebvre1@gmail.com" ).getId() );
		System.out.println( "will insert flight " + f );
		flightService.insertFlight( f, f.getUserId() );

		return new Object();
	}

	private Flight createAFlight() {
		Flight flight = new Flight();
		flight.setDate( LocalDate.of( 2020, 10, 18 ) );
		Airport dep = new Airport();
		Airport arr = new Airport();

		dep.setId( 119367 );
		arr.setId( 158263 );

		/*flight.setDepartureAirport( dep );
		flight.setArrivalAirport( arr );*/
		flight.setDepartureTime( LocalTime.now().minusHours( 2 ) );
		flight.setArrivalTime( LocalTime.now().minusMinutes( 30 ) );

		flight.setTotalTime(
		        (short) ( ChronoUnit.MINUTES.between( flight.getDepartureTime(), flight.getArrivalTime() ) ) );
		return flight;
	}

}
