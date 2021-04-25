package com.pilotair.clogbook.core.service;

import java.util.List;
import java.util.Set;

import com.pilotair.clogbook.core.dto.PilotDto;
import com.pilotair.clogbook.core.entity.Pilot;

public interface PilotService {

	Pilot save( Pilot pilot, Integer userId );

	Set<PilotDto> getDtoByOwnerId( int ownerId );

	List<Pilot> getAll();

	Pilot getById( int id );

	Pilot save( PilotDto pilotDto, Integer userId );

}
