package com.pilotair.clogbook.webapp.security.service;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.entity.User;
import com.pilotair.clogbook.core.repository.UserRepository;
import com.pilotair.clogbook.webapp.security.ApplicationUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private Logger			logger	= LoggerFactory.getLogger( UserDetailsServiceImpl.class );

	@Autowired
	private UserRepository	userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		logger.debug( "Loading user by username %s", username );
		User user = userRepository.findByUsername( username );
		if ( user == null )
			throw new UsernameNotFoundException( "Could not find user" );
		logger.debug( "Initializing roles for %s with HibernateUtils", username );
		Hibernate.initialize( user.getRole().getPermissions() );
		return new ApplicationUser( user );
	}

}
