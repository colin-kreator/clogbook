package com.pilotair.clogbook.core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Override
	@Transactional
	public Flight insertFlight( Flight flight ) {

		return flightRepository.save( flight );
	}

	@Override
	public List<Flight> getFlightsByUser( Integer userId ) {
		return flightRepository.findByUserIdOrderByDateDesc( userId );
	}

	@Override
	public Flight updateFlight( Flight flight ) {
		return flightRepository.save( flight );
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

}
