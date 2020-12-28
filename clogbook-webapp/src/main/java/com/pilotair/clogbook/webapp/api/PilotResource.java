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

import com.pilotair.clogbook.core.dto.PilotDto;
import com.pilotair.clogbook.core.service.PilotService;
import com.pilotair.clogbook.webapp.security.ApplicationUser;

@RestController
@RequestMapping( "/api/pilot" )
public class PilotResource {

	Logger					logger	= LoggerFactory.getLogger( PilotResource.class );

	@Autowired
	private PilotService	pilotService;

	@GetMapping
	@PreAuthorize( "hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" )
	public List<PilotDto> getUserPilotsDto( Authentication auth ) {
		logger.info( String.format( "User %s requests its pilots", auth.getName() ) );

		return pilotService.getDtoByOwnerId( ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId() );

	}

}
