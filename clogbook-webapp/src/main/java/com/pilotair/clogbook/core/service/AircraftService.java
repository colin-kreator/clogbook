package com.pilotair.clogbook.core.service;

import java.util.List;

import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.AircraftModel;

public interface AircraftService {

	List<Aircraft> getAllByUser( Integer userId );

	List<AircraftModel> getAllModelsByUser( Integer userId );

	Aircraft saveAircraft( Aircraft aircraft, Integer userId );

	AircraftModel saveAircraftModel( AircraftModel aircraftModel, Integer userId );

	Aircraft getById( Integer id );
}
