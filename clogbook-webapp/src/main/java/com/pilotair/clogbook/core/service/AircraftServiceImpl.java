package com.pilotair.clogbook.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.AircraftModel;
import com.pilotair.clogbook.core.repository.AircraftModelRepository;
import com.pilotair.clogbook.core.repository.AircraftRepository;

@Service
public class AircraftServiceImpl implements AircraftService {

	@Autowired
	private AircraftRepository		aircraftRepository;

	@Autowired
	private AircraftModelRepository	aircraftModelRepository;

	@Override
	public List<Aircraft> getAllByUser( Integer userId ) {
		return aircraftRepository.findByUserId( userId );
	}

	@Override
	public List<AircraftModel> getAllModelsByUser( Integer userId ) {
		return aircraftModelRepository.findByUserId( userId );
	}

	@Override
	public Aircraft saveAircraft( Aircraft aircraft, Integer userId ) {
		aircraft.setUserId( userId );

		if ( aircraft.getId() == null ) {
			/*
			 * Si l'aircraft semble etre nouveau (id null), on va voir si l'utilisateur n'a 
			 * pas déjà un avion avec la même immatriculation que celui donné
			 */
			Aircraft actCheck = aircraftRepository.findByUserIdAndRegistration( userId, aircraft.getRegistration() );
			if ( actCheck != null ) {
				aircraft.setId( actCheck.getId() );
			}

		}

		if ( aircraft.getAircraftModel() != null ) {
			saveAircraftModel( aircraft.getAircraftModel(), userId );
		}

		return aircraftRepository.save( aircraft );
	}

	@Override
	public AircraftModel saveAircraftModel( AircraftModel aircraftModel, Integer userId ) {
		aircraftModel.setUserId( userId );
		/*
		 * Si l'aircraftModel semble etre nouveau (id null), on va voir si l'utilisateur n'a 
		 * pas déjà un modele avec le même customName que celui donné
		 */
		if ( aircraftModel.getId() == null ) {
			AircraftModel amlCheck = aircraftModelRepository.findByUserIdAndCustomName( userId,
			        aircraftModel.getCustomName() );
			if ( amlCheck != null ) {
				aircraftModel.setId( amlCheck.getId() );
				return amlCheck;
			}

		}

		return aircraftModelRepository.save( aircraftModel );
	}

	@Override
	public Aircraft getById( Integer id ) {
		return aircraftRepository.findById( id ).orElse( null );
	}

}
