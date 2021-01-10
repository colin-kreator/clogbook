package com.pilotair.clogbook.core.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.AircraftModel;
import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.exception.InvalidCsvException;

@Service
public class CsvFileTreatmentServiceImpl implements CsvFileTreatmentService {

	@Autowired
	private FlightService	flightService;

	private final Logger	log	= LoggerFactory.getLogger( CsvFileTreatmentServiceImpl.class );

	private String[]		columnNames;

	private int				success;

	public int importFlightsFromCsv( MultipartFile file, Integer userId ) {
		// Normalize file name
		success = 0;

		String fileName = StringUtils.cleanPath( file.getOriginalFilename() );
		log.info( "Reading file " + fileName );
		if ( !fileName.toLowerCase().endsWith( ".csv" ) )
			throw new InvalidCsvException( "The file is not a csv file" );

		List<String> list = new ArrayList<>();

		try ( InputStream inputStream = file.getInputStream() ) {
			new BufferedReader( new InputStreamReader( inputStream, StandardCharsets.UTF_8 ) )
			        .lines()
			        .forEach( list::add );
		} catch ( IOException e ) {
			throw new InvalidCsvException( "Error reading the csv file", e );
		}

		columnNames = list.get( 0 ).split( ";" );
		list.remove( 0 );

		list.stream().map( this::getFlight ).forEach( f -> {
			if ( f != null )
				if ( flightService.insertFlight( f, userId ).getId() != null )
					success++;
		} );
		;

		return success;
	}

	private Flight getFlight( String str ) {
		String[] line = str.split( ";" );
		if ( line.length != columnNames.length )
			return null;

		Flight flt = new Flight();

		for ( int i = 0; i < line.length; i++ ) {
			String col = columnNames[i].trim();
			String lin = line[i].trim();
			try {
				if ( col.equals( "date" ) ) {
					if ( lin.length() == 8 )
						flt.setDate( LocalDate.parse( lin, DateTimeFormatter.ofPattern( "dd/MM/yy" ) ) );
					else if ( lin.length() == 10 )
						flt.setDate( LocalDate.parse( lin, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) );
				} else if ( col.equals( "aircraft_registration" ) ) {
					if ( flt.getAircraft() == null ) {
						Aircraft act = new Aircraft();
						act.setRegistration( lin );
						flt.setAircraft( act );
					} else {
						flt.getAircraft().setRegistration( lin );
					}
				} else if ( col.equals( "aircraft_type" ) ) {
					if ( flt.getAircraft() == null ) {
						Aircraft act = new Aircraft();
						AircraftModel aml = new AircraftModel();
						aml.setCustomName( lin );
						act.setAircraftModel( aml );
						flt.setAircraft( act );
					} else {
						AircraftModel aml = new AircraftModel();
						aml.setCustomName( lin );
						flt.getAircraft().setAircraftModel( aml );
					}
				} else if ( col.equals( "xx" ) ) {

				} else if ( col.equals( "xx" ) ) {

				} else if ( col.equals( "xx" ) ) {

				}

			} catch ( Exception e ) {
				return null;
			}
		}

		if ( flt.getDate() == null )
			return null;

		return flt;
	}

}
