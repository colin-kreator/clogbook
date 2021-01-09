package com.pilotair.clogbook.webapp.admin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilotair.clogbook.core.entity.auth.Role;
import com.pilotair.clogbook.core.service.RoleService;

@RestController
@RequestMapping( "/api/admin/role" )
public class RoleResource {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public Iterable<Role> getAllRoles() {
		return roleService.getAll();
	}

	@DeleteMapping( path = "/{id}" )
	public void deleteRole( @PathVariable( "id" ) Integer id ) {

	}

	@GetMapping( path = "/{id}" )
	public Role getRole( @PathVariable( "id" ) Integer id ) {
		return roleService.getById( id );
	}

	@PostMapping
	public Role addRole( @RequestBody Role role ) {
		return roleService.add( role );
	}
}
