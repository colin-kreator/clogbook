package com.pilotair.clogbook.core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.entity.User;
import com.pilotair.clogbook.core.entity.auth.Role;
import com.pilotair.clogbook.core.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	Logger							logger	= LoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private UserRepository			userRepository;

	@Autowired
	private RoleService	roleService;

	@Autowired
	private PasswordEncoder			passwordEncoder;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getAllUsersWithRole() {
		return userRepository.findWithRoleByIdNotNull();
	}

	/**
	 * Permet d'enregistrer un user en base de données. Attention, le mot de
	 * passe du User ne doit pas être encodé, l'encodage se passe ici.
	 */
	@Override
	@Transactional
	public User registerUser( User user ) {
		long top = System.currentTimeMillis();
		logger.debug( "Registereing new user" );
		if ( user.getRole() == null ) {
			throw new IllegalStateException( "User Role can not be null" );
		}

		if ( user.getRole().getId() == null ) {
			logger.trace( "Getting Role by name" );
			Role role = roleService.getByName( user.getRole().getName() );
			if ( role == null ) {
				throw new IllegalStateException( "User Role \"" + user.getRole().getName() + "\" has not been found" );
			} else {
				logger.trace( "setting role " + role.getId() + " to new user" );
				user.setRole( role );
			}
		}
		logger.debug( "Encoding user password" );
		user.setPassword( passwordEncoder.encode( user.getPassword() ) );
		logger.debug( "saving user" );
		userRepository.save( user );
		logger.debug( "set owner Id to userPilot with generated id" );
		user.setOwnerPilotId( user.getId() );
		// Pas utile de sauver une nouvelle fois l'user, l'entité n'est pas
		// detached
		// userRepository.save( user );
		logger.info( "New user registered, Time = " + ( System.currentTimeMillis() - top ) + " ms | " + user );
		return user;
	}

	@Override
	public User getByUserName( String username ) {
		return userRepository.findByUsername( username );
	}

	@Override
	public Boolean isEmailAlreadyInUse( String email ) {
		return userRepository.existsByUsername( email );
	}

}
