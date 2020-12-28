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

}
