package com.pilotair.clogbook.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.Aircraft;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

	List<Aircraft> findByUserId( Integer userId );

	Aircraft findByUserIdAndRegistration( Integer userId, String registration );
}
