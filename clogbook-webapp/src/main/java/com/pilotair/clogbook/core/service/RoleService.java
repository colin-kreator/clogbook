package com.pilotair.clogbook.core.service;

import java.util.List;

import com.pilotair.clogbook.core.entity.auth.Role;

public interface RoleService {

	/**
	 * @return CLogbookRole with permissions initialized
	 */
	List<Role> getAll();

	Role add( Role role );

	void delete( Integer id );

	/**
	 * @param name
	 *            the name of the role
	 * @return a CLogbookRole with permissions <b>not</b> initialized
	 */
	Role getByName( String name );

	/**
	 * @param id
	 *            the id of the role
	 * @return a CLogbookRole with permissions initialized
	 */
	Role getById( Integer id );
}
