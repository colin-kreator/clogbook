package com.pilotair.clogbook.core.service;

import java.util.List;

import com.pilotair.clogbook.core.entity.User;

public interface UserService {

	/**
	 * @return All users with dependants proxy not initialized
	 */
	List<User> getAllUsers();

	/**
	 * @return All users with role proxy initialized
	 */
	List<User> getAllUsersWithRole();

	User getByUserName( String username );

	User registerUser( User user );

	Boolean isEmailAlreadyInUse( String email );
}
