package com.pilotair.clogbook.webapp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilotair.clogbook.core.entity.User;
import com.pilotair.clogbook.core.service.UserService;

@RestController
@RequestMapping( "/api/admin/user" )
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	@PreAuthorize( "hasAuthority('ROLE_ADMIN')" )
	public List<User> getAllUsers() {
		return userService.getAllUsersWithRole();
	}

	@DeleteMapping( path = "/{id}" )
	public void deleteUser( @PathVariable( "id" ) Integer id ) {
		// TODO userService.deleteUser( id );

	}

	@PostMapping
	public void registerUser( @RequestBody User user ) {
		// userService.registerUser( user );

	}

}
