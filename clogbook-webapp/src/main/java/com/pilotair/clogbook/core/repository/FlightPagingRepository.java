package com.pilotair.clogbook.core.repository;

import java.util.List;

import com.pilotair.clogbook.core.entity.Flight;

public interface FlightPagingRepository {

	List<Flight> findFlightsForUser( Integer userId, Integer offset, Integer size );

}
