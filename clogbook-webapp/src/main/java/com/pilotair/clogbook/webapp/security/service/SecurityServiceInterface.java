package com.pilotair.clogbook.webapp.security.service;

public interface SecurityServiceInterface {

	String findLoggedInUsername();

	void autoLogin( String username, String password );
}