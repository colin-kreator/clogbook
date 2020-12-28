package com.pilotair.clogbook.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@EntityGraph( attributePaths = { "role" } )
	User findByUsername( String username );

	Boolean existsByUsername( String username );

	@EntityGraph( attributePaths = { "role" } )
	List<User> findWithRoleByIdNotNull();
}
