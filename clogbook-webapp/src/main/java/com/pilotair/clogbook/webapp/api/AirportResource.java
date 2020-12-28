package com.pilotair.clogbook.webapp.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilotair.clogbook.core.dto.AirportDto;
import com.pilotair.clogbook.core.service.AirportService;

@RestController
@RequestMapping( "/api/airport" )
public class AirportResource {

	Logger					logger	= LoggerFactory.getLogger( AirportResource.class );

	@Autowired
	private AirportService	airportService;

	@GetMapping( "/icao/{icao}" )
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" )
	public AirportDto getAirportByIcao( @PathVariable String icao, Authentication auth ) {
		logger.info( String.format( "User %s requests airport " + icao, auth.getName() ) );
		return airportService.getAirportDtoByIcao( icao.toUpperCase() );
	}

	@GetMapping( "/srss/{icao}/{date}" )
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" )
	public String[] getAirportSrSsByIcao( @PathVariable String icao, @PathVariable String date, Authentication auth ) {
		logger.info( String.format( "User %s requests airport sun rise sun set for" + icao, auth.getName() ) );
		return airportService.getAirportSrSsByIcao( icao.toUpperCase(), date );
	}

	@GetMapping
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" )
	public List<AirportDto> getAllAirports( Authentication auth ) {
		logger.info( String.format( "User %s requests all airports", auth.getName() ) );
		return airportService.getAll();
	}

}
