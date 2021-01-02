package com.pilotair.clogbook.core.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.AircraftModel;
import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.entity.Pilot;
import com.pilotair.clogbook.core.repository.AircraftModelRepository;
import com.pilotair.clogbook.core.repository.AircraftRepository;
import com.pilotair.clogbook.core.repository.FlightRepository;
import com.pilotair.clogbook.core.repository.PilotRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository		flightRepository;

	@Autowired
	private AircraftRepository		aircraftRepository;

	@Autowired
	private AircraftModelRepository	aircraftModelRepository;

	@Autowired
	private PilotRepository			pilotRepository;

	@Override
	@Transactional
	public Flight insertFlight( Flight flight, Integer userId ) {

		/*
		 * Gestion de l'avion
		 */
		Aircraft act = flight.getAircraft();
		if ( act != null && act.getId() == null ) {

			/*
			 * On vérifie d'abord si l'utilisateur a déjà un avion avec la même immatriculation pour éviter de créer un même avion 2 fois
			 */
			Aircraft actCheck = aircraftRepository.findByUserIdAndRegistration( userId, act.getRegistration() );
			if ( actCheck != null )
				act.setId( actCheck.getId() );

			/*
			 * Si l'avion est considéré comme nouveau, on fait le même traitement sur le aircraftModel avec le nom custom du modele
			 */
			if ( act.getId() == null ) {
				AircraftModel aml = act.getAircraftModel();
				if ( aml != null && aml.getId() == null ) {
					AircraftModel amlCheck = aircraftModelRepository.findByUserIdAndCustomName( userId,
					        aml.getCustomName() );
					if ( amlCheck != null )
						aml.setId( amlCheck.getId() );

					/*Si l'id du modele est toujours null, on crée un nouveau modele*/
					if ( aml.getId() == null ) {
						aml.setUserId( userId );
						aircraftModelRepository.save( aml );
					}
				}
				act.setUserId( userId );
				aircraftRepository.save( act );
			}

		}

		/* Mise à jour du type de l'avion si son type était précédemement null*/
		else if ( act != null && act.getId() != null && act.getAircraftModel() != null ) {

			System.out.println( "1" + act );

			Aircraft actCheck = aircraftRepository.findById( act.getId() ).orElseThrow();

			if ( actCheck.getAircraftModel() == null ) {

				System.out.println( "2" + actCheck );

				AircraftModel aml = act.getAircraftModel();
				if ( aml.getId() == null ) {
					AircraftModel amlCheck = aircraftModelRepository.findByUserIdAndCustomName( userId,
					        aml.getCustomName() );
					if ( amlCheck != null )
						aml.setId( amlCheck.getId() );

					/*Si l'id du modele est toujours null, on crée un nouveau modele*/
					if ( aml.getId() == null ) {
						aml.setUserId( userId );
						aircraftModelRepository.save( aml );
					}
				}
				actCheck.setAircraftModel( aml );
				aircraftRepository.save( actCheck );
			}
		}

		/*
		 * Gestion du commandant de bord
		 */
		Pilot pilot = flight.getPilot();

		if ( pilot != null && pilot.getId() == null ) {
			/*On vérifie si le pilote existe déjà (par le nom, pas par l'id)*/

			Pilot pltCheck = pilotRepository.findByOwnerPilotIdAndLastName( userId, pilot.getLastName() );
			if ( pltCheck != null )
				pilot.setId( pltCheck.getId() );

			if ( pilot.getId() == null ) {
				pilot.setOwnerPilotId( userId );
				pilotRepository.save( pilot );
			}

		}

		flight.setUserId( userId );
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
