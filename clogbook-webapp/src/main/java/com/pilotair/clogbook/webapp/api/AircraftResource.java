package com.pilotair.clogbook.webapp.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.service.AircraftService;
import com.pilotair.clogbook.webapp.security.ApplicationUser;

@RestController
@RequestMapping( "/api/aircraft" )
public class AircraftResource {

	Logger					logger	= LoggerFactory.getLogger( AircraftResource.class );

	@Autowired
	private AircraftService	aircraftService;

	/**
	 * Méthode qui permet de lister tous les avions de l'utilisateur qui le
	 * demande
	 * 
	 * @param auth
	 * @return la liste de tous les avions de l'utilisateur loggué
	 */
	@GetMapping
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" )
	public List<Aircraft> getUserAircrafts( Authentication auth ) {
		logger.info( String.format( "User %s requests its aircrafts", auth.getName() ) );
		return aircraftService.getAllByUser( ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId() );
	}

}
