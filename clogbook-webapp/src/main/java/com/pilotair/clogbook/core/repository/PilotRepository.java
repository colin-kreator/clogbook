package com.pilotair.clogbook.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.Pilot;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Integer> {

	List<Pilot> findByOwnerPilotId( Integer ownerId );

	Pilot findByOwnerPilotIdAndLastName( Integer ownerId, String lastName );

}
