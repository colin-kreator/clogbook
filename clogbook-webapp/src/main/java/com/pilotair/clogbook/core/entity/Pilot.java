package com.pilotair.clogbook.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table( name = "t_pilot_plt" )
@Inheritance( strategy = InheritanceType.JOINED )
public class Pilot {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "plt_id" )
	protected Integer	id;

	@Column( name = "plt_last_name" )
	protected String	lastName;

	@Column( name = "plt_first_name" )
	protected String	firstName;

	@Column( name = "plt_email" )
	protected String	email;

	@Column( name = "plt_phone_number" )
	protected String	phone;

	@Column( name = "plt_owner" )
	protected Integer	ownerPilotId;

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
		        + ", phone=" + phone + ", ownerPilotId=" + ownerPilotId + "]";
	}

	/**
	 * Bean Norm
	 */
	public Pilot() {
		super();
	}

	/**
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param phone
	 * @param ownerUserid
	 */
	public Pilot( String lastName, String firstName, String email, String phone, Integer ownerPilotId ) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.ownerPilotId = ownerPilotId;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId( Integer id ) {
		this.id = id;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone( String phone ) {
		this.phone = phone;
	}

	/**
	 * @return the ownerUserid
	 */
	public Integer getOwnerPilotId() {
		return ownerPilotId;
	}

	/**
	 * @param ownerUserid
	 *            the ownerUserid to set
	 */
	public void setOwnerPilotId( Integer ownerPilotid ) {
		this.ownerPilotId = ownerPilotid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( firstName == null ) ? 0 : firstName.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( lastName == null ) ? 0 : lastName.hashCode() );
		result = prime * result + ( ( ownerPilotId == null ) ? 0 : ownerPilotId.hashCode() );
		result = prime * result + ( ( phone == null ) ? 0 : phone.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Pilot other = (Pilot) obj;
		if ( email == null ) {
			if ( other.email != null )
				return false;
		} else if ( !email.equals( other.email ) )
			return false;
		if ( firstName == null ) {
			if ( other.firstName != null )
				return false;
		} else if ( !firstName.equals( other.firstName ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( lastName == null ) {
			if ( other.lastName != null )
				return false;
		} else if ( !lastName.equals( other.lastName ) )
			return false;
		if ( ownerPilotId == null ) {
			if ( other.ownerPilotId != null )
				return false;
		} else if ( !ownerPilotId.equals( other.ownerPilotId ) )
			return false;
		if ( phone == null ) {
			if ( other.phone != null )
				return false;
		} else if ( !phone.equals( other.phone ) )
			return false;
		return true;
	}

}
