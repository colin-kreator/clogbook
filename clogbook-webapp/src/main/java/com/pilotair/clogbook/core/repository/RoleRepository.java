package com.pilotair.clogbook.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.auth.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName( String name );
}
