package com.pilotair.clogbook.core.service;

import java.util.List;

import com.pilotair.clogbook.core.entity.Flight;

public interface FlightService {

	Flight insertFlight( Flight flight, Integer userId );

	List<Flight> getFlightsByUser( Integer userId );

	List<Flight> getLast20FlightsByUser( Integer userId );

	List<Flight> getAllFlights();

	Flight updateFlight( Flight flight );

	List<String> getUserSimTypes( Integer userId );

	void deleteByUserId( Integer userId );

	List<Flight> findFlightsForUser( Integer userId, Integer offset, Integer size, boolean includePilot,
	        boolean includeAircraft, boolean includeAirports );

	void deleteById( Integer id );

}
