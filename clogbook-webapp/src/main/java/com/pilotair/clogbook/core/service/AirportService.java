package com.pilotair.clogbook.core.service;

import java.util.List;

import com.pilotair.clogbook.core.dto.AirportDto;
import com.pilotair.clogbook.core.entity.Airport;

public interface AirportService {

	List<AirportDto> getAllByIcaoStartingWith( String icao );

	List<AirportDto> getAll();

	AirportDto getAirportDtoByIcao( String icao );

	Airport getAirportByIcao( String icao );

	String[] getAirportSrSsByIcao( String icao, String date );
}
