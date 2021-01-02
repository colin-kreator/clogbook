package com.pilotair.clogbook.core.service;

import java.util.List;

import com.pilotair.clogbook.core.entity.Flight;

public interface FlightService {

	Flight insertFlight( Flight flight, Integer userId );

	List<Flight> getFlightsByUser( Integer userId );

	List<Flight> getAllFlights();

	Flight updateFlight( Flight flight );

	List<String> getUserSimTypes( Integer userId );

}
