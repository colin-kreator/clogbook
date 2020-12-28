package com.pilotair.clogbook.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "T_AIRCRAFT_ACT" )
public class Aircraft {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "act_id" )
	private Integer			id;

	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn( name = "act_aml_id" )
	private AircraftModel	aircraftModel;

	@Column( name = "act_registration" )
	private String			registration;

	@Column( name = "act_usr_id" )
	private Integer			userId;

	/**
	 * Bean norm
	 */
	public Aircraft() {
		super();
	}

	/**
	 * @param aircraftModel
	 * @param registration
	 * @param userId
	 */
	public Aircraft( AircraftModel aircraftModel, String registration, Integer userId ) {
		super();
		this.aircraftModel = aircraftModel;
		this.registration = registration;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Aircraft [id=" + id + ", aircraftModel=" + aircraftModel + ", registration=" + registration
		        + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( aircraftModel == null ) ? 0 : aircraftModel.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( registration == null ) ? 0 : registration.hashCode() );
		result = prime * result + ( ( userId == null ) ? 0 : userId.hashCode() );
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
		Aircraft other = (Aircraft) obj;
		if ( aircraftModel == null ) {
			if ( other.aircraftModel != null )
				return false;
		} else if ( !aircraftModel.equals( other.aircraftModel ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( registration == null ) {
			if ( other.registration != null )
				return false;
		} else if ( !registration.equals( other.registration ) )
			return false;
		if ( userId == null ) {
			if ( other.userId != null )
				return false;
		} else if ( !userId.equals( other.userId ) )
			return false;
		return true;
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
	 * @return the aircraftModel
	 */
	public AircraftModel getAircraftModel() {
		return aircraftModel;
	}

	/**
	 * @param aircraftModel
	 *            the aircraftModel to set
	 */
	public void setAircraftModel( AircraftModel aircraftModel ) {
		this.aircraftModel = aircraftModel;
	}

	/**
	 * @return the registration
	 */
	public String getRegistration() {
		return registration;
	}

	/**
	 * @param registration
	 *            the registration to set
	 */
	public void setRegistration( String registration ) {
		this.registration = registration;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId( Integer userId ) {
		this.userId = userId;
	}

}
