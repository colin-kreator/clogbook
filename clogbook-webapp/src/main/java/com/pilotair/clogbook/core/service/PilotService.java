package com.pilotair.clogbook.core.service;

import java.util.List;

import com.pilotair.clogbook.core.dto.PilotDto;
import com.pilotair.clogbook.core.entity.Pilot;

public interface PilotService {

	Pilot add( Pilot pilot );

	List<PilotDto> getDtoByOwnerId( int ownerId );

	List<Pilot> getAll();

	Pilot getById( int id );

}
