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

import com.pilotair.clogbook.core.entity.AircraftModel;
import com.pilotair.clogbook.core.service.AircraftService;
import com.pilotair.clogbook.webapp.security.ApplicationUser;

@RestController
@RequestMapping( "/api/aircraftmodel" )
public class AircraftModelResource {

	Logger					logger	= LoggerFactory.getLogger( FlightResource.class );

	@Autowired
	private AircraftService	aircraftService;

	/**
	 * Méthode qui permet de lister tous les modeles d'avion de l'utilisateur
	 * qui le demande
	 * 
	 * @param auth
	 * @return la liste de tous les modeles d'avions de l'utilisateur loggué
	 */
	@GetMapping
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" )
	public List<AircraftModel> getUserAircraftModels( Authentication auth ) {
		logger.info( String.format( "User %s requests its flights", auth.getName() ) );
		return aircraftService.getAllModelsByUser( ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId() );
	}

}
