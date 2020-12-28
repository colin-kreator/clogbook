package com.pilotair.clogbook.core.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.dto.AirportDto;
import com.pilotair.clogbook.core.entity.Airport;
import com.pilotair.clogbook.core.entity.srss.SrSsEntity;
import com.pilotair.clogbook.core.repository.AirportRepository;
import com.pilotair.clogbook.core.repository.SunRiseAndSunSetRepository;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportRepository			airportRepository;

	@Autowired
	private SunRiseAndSunSetRepository	sunRiseAndSunSetRepository;

	@Override
	public List<AirportDto> getAllByIcaoStartingWith( String icao ) {
		List<Airport> apts = airportRepository.findByIcaoCodeStartsWithOrderByIcaoCode( icao.toUpperCase() );
		List<AirportDto> dtos = new ArrayList<AirportDto>();
		apts.forEach(
		        apt -> dtos.add( new AirportDto( apt.getId(), apt.getName(), apt.getIcaoCode(), apt.getIataCode() ) ) );
		return dtos;
	}

	@Override
	public List<AirportDto> getAll() {
		List<Airport> apts = airportRepository.findAll();
		List<AirportDto> dtos = new ArrayList<AirportDto>();
		apts.forEach(
		        apt -> dtos.add( new AirportDto( apt.getId(), apt.getName(), apt.getIcaoCode(), apt.getIataCode() ) ) );
		return dtos;
	}

	@Override
	public AirportDto getAirportDtoByIcao( String icao ) {
		Airport apt = airportRepository.findByIcaoCode( icao );
		if ( apt == null )
			return null;
		return new AirportDto( apt.getId(), apt.getName(), apt.getIcaoCode(), apt.getIataCode() );
	}

	@Override
	public Airport getAirportByIcao( String icao ) {
		return airportRepository.findByIcaoCode( icao );
	}

	@Override
	public String[] getAirportSrSsByIcao( String icao, String date ) {
		Airport apt = airportRepository.findByIcaoCode( icao );
		if ( apt == null )
			return null;

		LocalDate localDate = LocalDate.parse( date, DateTimeFormatter.ofPattern( "dd-MM-yy" ) );

		SrSsEntity srSs = sunRiseAndSunSetRepository.getSrSs( apt.getLatitude(), apt.getLongitude(), localDate );

		DateTimeFormatter parser = DateTimeFormatter.ofPattern( "h:mm:ss a" );
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "HH:mm" );

		String[] ret = { LocalTime.parse( srSs.getSunRise(), parser ).format( formatter ),
		                 LocalTime.parse( srSs.getSunSet(), parser ).format( formatter ) };

		return ret;
	}
}
