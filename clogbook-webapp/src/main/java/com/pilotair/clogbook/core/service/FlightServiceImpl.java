package com.pilotair.clogbook.core.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.entity.Pilot;
import com.pilotair.clogbook.core.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository	flightRepository;

	@Autowired
	private AircraftService		aircraftService;

	@Autowired
	private PilotService		pilotService;

	@Override
	@Transactional
	public Flight insertFlight( Flight flight, Integer userId ) {
		flight.setUserId( userId );

		/*
		 * Gestion de l'avion
		 */
		Aircraft act = flight.getAircraft();
		if ( act != null ) {
			aircraftService.saveAircraft( act, userId );
		}

		/*
		 * Gestion du commandant de bord
		 */
		Pilot pilot = flight.getPilot();
		if ( pilot != null ) {
			pilotService.save( pilot, userId );
		}

		return flightRepository.save( flight );
	}

	@Override
	public List<String> getUserSimTypes( Integer userId ) {
		LocalDate dateStart = LocalDate.now().minus( 5, ChronoUnit.YEARS );
		return flightRepository.findDistinctSimTypeByUserId( userId, dateStart, LocalDate.now() );
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
