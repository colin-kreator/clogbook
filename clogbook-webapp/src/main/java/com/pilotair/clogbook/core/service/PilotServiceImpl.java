package com.pilotair.clogbook.core.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
			Pilot pltCheck = pilotRepository.findByOwnerPilotIdAndLastNameIgnoreCase( userId, pilot.getLastName() );
			if ( pltCheck != null ) {
				pilot.setId( pltCheck.getId() );
				return pltCheck;
			}

		}
		return pilotRepository.save( pilot );
	}

	@Override
	public Pilot save( PilotDto pilotDto, Integer userId ) {
		// On sauvegarde le piloteDto seulement si il n'existe pas

		if ( pilotDto.getId() == null ) {
			/*
			 * Si le pilote semble etre nouveau (id null), on va voir si l'utilisateur n'a 
			 * pas déjà un pilote avec le même nom que celui donné
			 */
			Pilot pltCheck = pilotRepository.findByOwnerPilotIdAndLastNameIgnoreCase( userId, pilotDto.getLastName() );
			if ( pltCheck != null ) {
				return pltCheck;
			}

			/*Si le pilot n'existe vraiment pas on le crée*/
			Pilot pilot = new Pilot();
			pilot.setLastName( pilotDto.getLastName() );
			pilot.setFirstName( pilotDto.getFirstName() );
			pilot.setOwnerPilotId( userId );

			return pilotRepository.save( pilot );
		}
		return pilotRepository.findById( pilotDto.getId() ).orElse( null );
	}

	@Override
	public Set<PilotDto> getDtoByOwnerId( int ownerId ) {
		Set<Pilot> pilots = new HashSet<>();

		pilotRepository.findByOwnerPilotId( ownerId ).forEach( pilots::add );

		return pilots.stream().map( plt -> {
			PilotDto dto = new PilotDto( plt.getId(), plt.getLastName(), plt.getFirstName() );
			if ( dto.getId() == ownerId ) {
				dto.setLastName( "(" + plt.getFirstName() + " " + plt.getLastName() + ")" );
				dto.setFirstName( "Self" );
			}
			return dto;
		} ).collect( Collectors.toSet() );
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
