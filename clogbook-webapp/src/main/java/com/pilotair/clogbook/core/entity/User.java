package com.pilotair.clogbook.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pilotair.clogbook.core.entity.auth.Role;

@Entity
@Table( name = "ts_user_usr" )
@PrimaryKeyJoinColumn( name = "usr_id" )
public class User extends Pilot {

	@JoinColumn( name = "usr_rol_id" )
	@ManyToOne
	private Role		role;

	@Column( name = "usr_username" )
	private String		username;

	@Column( name = "usr_password" )
	private String		password;

	@Column( name = "usr_enabled" )
	private Boolean		enabled;

	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
	@JoinColumn( name = "plt_owner" )
	private List<Pilot>	pilots;

	@Override
	public String toString() {
		return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
		        + ", phone=" + phone + ", ownerPilotId=" + ownerPilotId + ", role=" + role + ", username=" + username
		        + ", password=" + password + ", enabled=" + enabled + ", pilots=" + pilots + "]";
	}

	/**
	 * Bean Norm
	 */
	public User() {
		super();
	}

	/**
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param phone
	 * @param ownerPilotId
	 * @param role
	 * @param username
	 * @param password
	 * @param enabled
	 */
	public User( String lastName, String firstName, String email, String phone, Integer ownerPilotId, Role role,
	        String username, String password, Boolean enabled ) {
		super( lastName, firstName, email, phone, ownerPilotId );
		this.role = role;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole( Role role ) {
		this.role = role;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername( String username ) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled( Boolean enabled ) {
		this.enabled = enabled;
	}

	/**
	 * @return the pilots
	 */
	public List<Pilot> getPilots() {
		return pilots;
	}

	/**
	 * @param pilots
	 *            the pilots to set
	 */
	public void setPilots( List<Pilot> pilots ) {
		this.pilots = pilots;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( password == null ) ? 0 : password.hashCode() );
		result = prime * result + ( ( pilots == null ) ? 0 : pilots.hashCode() );
		result = prime * result + ( ( role == null ) ? 0 : role.hashCode() );
		result = prime * result + ( ( username == null ) ? 0 : username.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( !super.equals( obj ) )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		User other = (User) obj;
		if ( password == null ) {
			if ( other.password != null )
				return false;
		} else if ( !password.equals( other.password ) )
			return false;
		if ( pilots == null ) {
			if ( other.pilots != null )
				return false;
		} else if ( !pilots.equals( other.pilots ) )
			return false;
		if ( role == null ) {
			if ( other.role != null )
				return false;
		} else if ( !role.equals( other.role ) )
			return false;
		if ( username == null ) {
			if ( other.username != null )
				return false;
		} else if ( !username.equals( other.username ) )
			return false;
		return true;
	}

}
