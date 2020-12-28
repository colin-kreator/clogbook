package com.pilotair.clogbook.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.auth.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	public Permission findByContextAndOperation( String context, String operation );

}
