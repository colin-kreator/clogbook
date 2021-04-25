package com.pilotair.clogbook.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilotair.clogbook.core.StatType;
import com.pilotair.clogbook.core.service.StatService;
import com.pilotair.clogbook.webapp.security.ApplicationUser;

@RestController
@RequestMapping( "api/stat" )
public class StatResource {

	@Autowired
	private StatService statService;

	@GetMapping( "cnt/{value}" )
	public String[] getCounterStat( @PathVariable( "value" ) StatType statType,
	        Authentication auth ) {
		Integer userId = ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId();
		return statService.getCounterStat( statType, userId );
	}

}
