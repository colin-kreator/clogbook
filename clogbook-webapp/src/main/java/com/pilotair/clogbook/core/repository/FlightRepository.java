package com.pilotair.clogbook.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	List<Flight> findByUserIdOrderByDateDesc( Integer userId );

}
