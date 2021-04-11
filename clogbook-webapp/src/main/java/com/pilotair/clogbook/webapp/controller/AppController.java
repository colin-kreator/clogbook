package com.pilotair.clogbook.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/app" )
public class AppController {

	@GetMapping( "dashboard" )
	public String displayDashboard() {
		return "app/dashboard";
	}

}