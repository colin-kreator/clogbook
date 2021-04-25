package com.pilotair.clogbook.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pilotair.clogbook.core.StatType;
import com.pilotair.clogbook.core.service.AircraftService;
import com.pilotair.clogbook.webapp.security.ApplicationUser;

@Controller
@RequestMapping( "/app" )
public class AppController {

	@Autowired
	private AircraftService aircraftService;

	@GetMapping( "dashboard" )
	public String displayDashboard( Authentication auth, Model model ) {
		Integer userId = ( (ApplicationUser) ( auth.getPrincipal() ) ).getUser().getId();

		List<StatType> statTypes = new ArrayList<>();
		statTypes.add( StatType.TOTAL_HOURS );
		statTypes.add( StatType.NIGHT_HOURS );
		statTypes.add( StatType.IFR_HOURS );
		statTypes.add( StatType.SE_HOURS );
		statTypes.add( StatType.ME_HOURS );

		model.addAttribute( "statTypes", statTypes );

		// model.addAttribute( "aircrafts", aircraftService.getAllByUser( userId
		// ) );
		// model.addAttribute( "aircraftModels",
		// aircraftService.getAllModelsByUser( userId ) );

		return "app/dashboard";
	}

}
