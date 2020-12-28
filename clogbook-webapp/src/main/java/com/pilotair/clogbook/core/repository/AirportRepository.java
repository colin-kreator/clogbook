package com.pilotair.clogbook.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pilotair.clogbook.core.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

	List<Airport> findByIcaoCodeStartsWithOrderByIcaoCode( String str );

	Airport findByIcaoCode( String icao );

}
