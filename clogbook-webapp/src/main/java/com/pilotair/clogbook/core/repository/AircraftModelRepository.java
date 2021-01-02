package com.pilotair.clogbook.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.AircraftModel;

@Repository
public interface AircraftModelRepository extends JpaRepository<AircraftModel, Integer> {

	List<AircraftModel> findByUserId( Integer userId );

	AircraftModel findByUserIdAndCustomName( Integer userId, String customName );
}
