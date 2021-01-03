package com.pilotair.clogbook.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.dto.PilotDto;
import com.pilotair.clogbook.core.entity.Pilot;
import com.pilotair.clogbook.core.repository.PilotRepository;

@Service
public class PilotServiceImpl implements PilotService {

	@Autowired
	private PilotRepository pilotRepository;

	@Override
	public Pilot save( Pilot pilot, Integer userId ) {
		pilot.setOwnerPilotId( userId );
		if ( pilot.getId() == null ) {
			/*
			 * Si le pilote semble etre nouveau (id null), on va voir si l'utilisateur n'a 
			 * pas déjà un pilote avec le même nom que celui donné
			 */
			Pilot pltCheck = pilotRepository.findByOwnerPilotIdAndLastName( userId, pilot.getLastName() );
			if ( pltCheck != null ) {
				pilot.setId( pltCheck.getId() );
				return pltCheck;
			}

		}

		return pilotRepository.save( pilot );
	}

	@Override
	public List<PilotDto> getDtoByOwnerId( int ownerId ) {
		List<Pilot> pilots = pilotRepository.findByOwnerPilotId( ownerId );

		return pilots.stream().map( plt -> {
			PilotDto dto = new PilotDto( plt.getId(), plt.getLastName(), plt.getFirstName() );
			if ( dto.getId() == ownerId ) {
				dto.setLastName( "(" + plt.getFirstName() + " " + plt.getLastName() + ")" );
				dto.setFirstName( "Self" );
			}
			return dto;
		} ).collect( Collectors.toList() );
	}

	@Override
	public List<Pilot> getAll() {
		return pilotRepository.findAll();
	}

	@Override
	public Pilot getById( int id ) {
		return pilotRepository.findById( id ).orElseThrow();
	}

}
