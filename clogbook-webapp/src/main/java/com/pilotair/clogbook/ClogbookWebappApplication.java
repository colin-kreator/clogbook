package com.pilotair.clogbook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.AircraftModel;
import com.pilotair.clogbook.core.entity.Airport;
import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.entity.Pilot;
import com.pilotair.clogbook.core.entity.User;
import com.pilotair.clogbook.core.repository.AircraftRepository;
import com.pilotair.clogbook.core.repository.AirportRepository;
import com.pilotair.clogbook.core.repository.PilotRepository;
import com.pilotair.clogbook.core.repository.UserRepository;
import com.pilotair.clogbook.core.service.AircraftService;
import com.pilotair.clogbook.core.service.FlightService;
import com.pilotair.clogbook.webapp.file.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties( { FileStorageProperties.class } )
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
	public Object LoadSampleFlights( FlightService flightService, UserRepository userRepository,
	        PilotRepository pilotRepository, AirportRepository airportRepository,
	        AircraftService aircraftService, AircraftRepository aircraftRepository ) {

		User colin = userRepository.findByUsername( "colin.lefebvre1@gmail.com" );

		Pilot ken = new Pilot( "POWELL", "Ken", "ken.powell@flybe.com", null, colin.getId() );
		pilotRepository.save( ken );

		Aircraft e95 = new Aircraft( new AircraftModel( colin.getId(), "Embraer", "E190", "E95", true, true ), "G-FBEH",
		        colin.getId() );
		aircraftService.saveAircraft( e95, colin.getId() );

		Aircraft da40 = aircraftRepository.findByUserIdAndRegistration( colin.getId(), "F-HZIC" );

		Airport dep1 = airportRepository.findByIcaoCode( "EGCC" );
		Airport dep2 = airportRepository.findByIcaoCode( "EGBB" );
		Airport arr1 = airportRepository.findByIcaoCode( "LFLS" );
		Airport arr2 = airportRepository.findByIcaoCode( "LFBO" );

		flightService.deleteByUserId( colin.getId() );

		Flight[] f = new Flight[50];
		Random r = new Random();
		for ( int i = 0; i < f.length; i++ ) {
			f[i] = new Flight();

			f[i].setDepartureTime( LocalTime.of( r.nextInt( 24 ), r.nextInt( 60 ) ) );

			f[i].setDate( LocalDate.of( 1993, r.nextInt( 12 ) + 1, r.nextInt( 28 ) + 1 ) );

			f[i].setTotalTime( (short) ( r.nextInt( 120 ) + 45 ) );

			f[i].setArrivalTime( f[i].getDepartureTime().plusMinutes( f[i].getTotalTime() ) );
			if ( r.nextBoolean() ) {
				f[i].setNightTime( (short) ( f[i].getTotalTime() - r.nextInt( 45 ) ) );
			}

			if ( r.nextBoolean() ) {
				f[i].setCrossCountryTime( f[i].getTotalTime() );
				f[i].setPilot( ken );
				f[i].setAircraft( e95 );
				f[i].setMultiEngine( true );
				f[i].setMultiPilot( true );
				f[i].setCopilotTime( f[i].getTotalTime() );
				f[i].setInstrumentTime( f[i].getTotalTime() );
			} else {
				f[i].setPilot( colin );
				f[i].setAircraft( da40 );
				f[i].setPicTime( f[i].getTotalTime() );
			}
			if ( r.nextBoolean() ) {
				if ( r.nextBoolean() ) {
					f[i].setDepartureAirport( dep1 );
					f[i].setArrivalAirport( arr1 );
				} else {
					f[i].setDepartureAirport( dep1 );
					f[i].setArrivalAirport( arr2 );
				}
			} else {
				if ( r.nextBoolean() ) {
					f[i].setDepartureAirport( dep2 );
					f[i].setArrivalAirport( arr1 );
				} else {
					f[i].setDepartureAirport( dep2 );
					f[i].setArrivalAirport( arr2 );
				}
			}

			flightService.insertFlight( f[i], colin.getId() );
		}

		return new Object();
	}

}
