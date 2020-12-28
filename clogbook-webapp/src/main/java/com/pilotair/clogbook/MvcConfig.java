package com.pilotair.clogbook;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers( ViewControllerRegistry registry ) {
		WebMvcConfigurer.super.addViewControllers( registry );
		registry.addViewController( "/login" ).setViewName( "login" );
		registry.addViewController( "/app/dashboard" ).setViewName( "dashboard" );
		// registry.addViewController( "/hello" ).setViewName( "hello" );
		// registry.addViewController( "/login" ).setViewName(
		// "authentification/login" );
	}
}
