package com.pilotair.clogbook.core.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pilotair.clogbook.core.dto.PilotDto;
import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.entity.Pilot;
import com.pilotair.clogbook.core.repository.FlightPagingRepository;
import com.pilotair.clogbook.core.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository		flightRepository;

	@Autowired
	private FlightPagingRepository	flightPagingRepository;

	@Autowired
	private AircraftService			aircraftService;

	@Autowired
	private PilotService			pilotService;

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
		PilotDto pilotDto = flight.getPilotDto();
		if ( pilotDto != null ) {
			flight.setPilot( pilotService.save( pilotDto, userId ) );
		}

		System.err.println( flight.getDate() );
		return flightRepository.save( flight );
	}

	@Override
	@Transactional( readOnly = true )
	public List<Flight> findFlightsForUser( Integer userId,
	        Integer offset, Integer size,
	        boolean includePilot, boolean includeAircraft, boolean includeAirports ) {
		List<Flight> flights = flightPagingRepository.findFlightsForUser( userId, offset, size );
		flights.forEach( flt -> {
			if ( includeAircraft )
				Hibernate.initialize( flt.getAircraft() );
			if ( includePilot )
				Hibernate.initialize( flt.getPilot() );
			if ( includeAirports ) {
				Hibernate.initialize( flt.getDepartureAirport() );
				Hibernate.initialize( flt.getArrivalAirport() );
			}

			if ( flt.getAircraft() != null ) {
				if ( flt.getAircraft().getAircraftModel() != null )
					flt.getAircraft().getAircraftModel().getCustomName();
			}
			if ( flt.getDepartureAirport() != null )
				flt.getDepartureAirport().getIataCode();
			if ( flt.getArrivalAirport() != null )
				flt.getArrivalAirport().getIataCode();

			Pilot p = flt.getPilot();
			if ( p != null ) {
				if ( p.getId().equals( userId ) ) {
					/*
					 * In the particular case that the pilot is the user itself, we replace its name by "Self"
					 */
					flt.setPilotDto( new PilotDto( p.getId(), "Self", "" ) );
				} else {
					flt.setPilotDto( new PilotDto( p.getId(), p.getLastName(), p.getFirstName() ) );
				}
			}
		} );

		return flights;
	}

	@Override
	public List<String> getUserSimTypes( Integer userId ) {
		LocalDate dateStart = LocalDate.now().minus( 5, ChronoUnit.YEARS );
		return flightRepository.findDistinctSimTypeByUserId( userId, dateStart, LocalDate.now() );
	}

	@Override
	public List<Flight> getFlightsByUser( Integer userId ) {
		return flightRepository.findByUserIdOrderByDateDescDepartureTimeDesc( userId );
	}

	@Override
	public Flight updateFlight( Flight flight ) {
		return flightRepository.save( flight );
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	@Transactional
	public List<Flight> getLast20FlightsByUser( Integer userId ) {
		List<Flight> flts = flightRepository.findTop20ByUserIdOrderByDateDescDepartureTimeDesc( userId );

		/*
		 * Initialization of Hibernate proxys
		 */
		flts.forEach( f -> {

			if ( f.getAircraft() != null ) {
				if ( f.getAircraft().getAircraftModel() != null )
					f.getAircraft().getAircraftModel().getCustomName();
			}
			if ( f.getDepartureAirport() != null )
				f.getDepartureAirport().getIataCode();
			if ( f.getArrivalAirport() != null )
				f.getArrivalAirport().getIataCode();

			Pilot p = f.getPilot();
			if ( p != null ) {
				if ( p.getId().equals( userId ) ) {
					/*
					 * In the particular case that the pilot is the user itself, we replace its name by "Self"
					 */
					f.setPilotDto( new PilotDto( p.getId(), "Self", "" ) );
				} else {
					f.setPilotDto( new PilotDto( p.getId(), p.getLastName(), p.getFirstName() ) );
				}
			}
		} );

		return flts;
	}

	@Override
	@Transactional
	public void deleteByUserId( Integer userId ) {
		flightRepository.deleteByUserId( userId );
	}

	@Override
	@Transactional
	public void deleteById( Integer id ) {
		flightRepository.deleteById( id );
	}

}
