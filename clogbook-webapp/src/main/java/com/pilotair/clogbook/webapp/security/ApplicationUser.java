package com.pilotair.clogbook.webapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pilotair.clogbook.core.entity.User;
import com.pilotair.clogbook.core.entity.auth.Role;

public class ApplicationUser implements UserDetails {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -413060251893940802L;

	private User				user;

	/**
	 * @param user
	 */
	public ApplicationUser( User user ) {
		super();
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role role = user.getRole();
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		role.getPermissions().forEach( p -> authorities.add(
		        new SimpleGrantedAuthority( p.getContext().toLowerCase() + ":" + p.getOperation().toLowerCase() ) ) );
		authorities.add( new SimpleGrantedAuthority( "ROLE_" + role.getName().toUpperCase() ) );

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
