package com.pilotair.clogbook.core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.entity.auth.Permission;
import com.pilotair.clogbook.core.entity.auth.Role;
import com.pilotair.clogbook.core.repository.PermissionRepository;
import com.pilotair.clogbook.core.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository			roleRepository;

	@Autowired
	private PermissionRepository	permissionRepository;

	@Override
	@Transactional
	public List<Role> getAll() {
		List<Role> roles = roleRepository.findAll();
		roles.stream().forEach( r -> Hibernate.initialize( r.getPermissions() ) );
		return roles;
	}

	@Override
	@Transactional
	public Role add( Role role ) {

		if ( role.getPermissions() != null ) {
			for ( Permission permToPersist : role.getPermissions() ) {
				if ( permToPersist.getId() == null ) {
					Permission perm = permissionRepository.findByContextAndOperation(
					        permToPersist.getContext(),
					        permToPersist.getOperation() );
					if ( perm == null ) {
						permissionRepository.save( permToPersist );
						System.out.println( "Permission has been saved : " + permToPersist );
					} else {
						permToPersist.setId( perm.getId() );
					}
				}
			}
		}
		roleRepository.save( role );
		System.out.println( "Role has been saved : " + role );
		return role;
	}

	@Override
	public void delete( Integer id ) {
		roleRepository.deleteById( id );
	}

	@Override
	public Role getByName( String name ) {
		return roleRepository.findByName( name );
	}

	@Override
	public Role getById( Integer id ) {
		Role role = roleRepository.findById( id ).orElseThrow();
		Hibernate.initialize( role.getPermissions() );
		return role;
	}

}
