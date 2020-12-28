package com.pilotair.clogbook.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pilotair.clogbook.webapp.security.service.UserDetailsServiceImpl;

@Configuration
public class AuthConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	@Autowired
	public DaoAuthenticationProvider authenticationProvider( PasswordEncoder passwordEncoder,
	        UserDetailsService userDetailsService ) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService( userDetailsService );
		authProvider.setPasswordEncoder( passwordEncoder );
		return authProvider;
	}
}
