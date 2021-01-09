package com.pilotair.clogbook.webapp.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.service.FlightService;
import com.pilotair.clogbook.webapp.form.FlightForm;
import com.pilotair.clogbook.webapp.security.ApplicationUser;

@RestController
@RequestMapping( "/api/flight" )
public class FlightResource {

	Logger					logger	= LoggerFactory.getLogger( FlightResource.class );

	@Autowired
	private FlightService	flightService;

	@PostMapping
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'flight:create')" )
	public Flight add( @RequestBody FlightForm flightForm, Authentication auth ) {
		Flight flight = flightForm.createFlight();
		logger.info( String.format( "User %s is saving a new flight : %s", auth.getName(), flight.toString() ) );
		return flightService.insertFlight( flight, ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId() );
	}

	/**
	 * Méthode qui permet de lister les 20 derniers vols de l'utilisateur qui le
	 * demande
	 * 
	 * @param auth
	 * @return la liste des 20 derniers vols de l'utilisateur loggué
	 */
	@GetMapping( "/top" )
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'flight:read')" )
	public List<Flight> getLastUserFlights( Authentication auth ) {
		logger.info( String.format( "User %s requests its last flights", auth.getName() ) );
		Integer userId = ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId();
		return flightService.getLast20FlightsByUser( userId );
	}

	/**
	 * Méthode qui permet de lister tous les vols de l'utilisateur qui le
	 * demande
	 * 
	 * @param auth
	 * @return la liste de tous les vols de l'utilisateur loggué
	 */
	@GetMapping
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'flight:read')" )
	public List<Flight> getUserFlights( Authentication auth ) {
		logger.info( String.format( "User %s requests its flights", auth.getName() ) );
		return flightService.getFlightsByUser( ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId() );
	}

	/**
	 * Méthode qui permet de lister tous les vols de tous les utilisateurs
	 * 
	 * @param auth
	 * @return la liste de tous les vols de tous les utilisateurs
	 */
	@GetMapping( "/all" )
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'flight:read-all')" )
	public List<Flight> getAllFlights( Authentication auth ) {
		logger.info( String.format( "User %s requests all flights", auth.getName() ) );
		return flightService.getAllFlights();
	}

	@GetMapping( "/simtype" )
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'flight:read')" )
	public List<String> getUserSimTypes( Authentication auth ) {
		logger.info( String.format( "User %s is getting its sim types", auth.getName() ) );
		return flightService.getUserSimTypes( ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId() );

	}

}
