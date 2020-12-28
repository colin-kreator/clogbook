package com.pilotair.clogbook.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_AIRCRAFT_MODEL_AML" )
public class AircraftModel {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "aml_id" )
	private Integer	id;

	@Column( name = "aml_usr_id" )
	private Integer	userId;

	@Column( name = "aml_brand" )
	private String	brand;

	@Column( name = "aml_model" )
	private String	model;

	@Column( name = "aml_custom_name" )
	private String	customName;

	@Column( name = "aml_multi_engine" )
	private Boolean	multiEngine;

	@Column( name = "aml_multi_pilot" )
	private Boolean	multiPilot;

	/**
	 * 
	 */
	public AircraftModel() {
		super();
	}

	/**
	 * @param userId
	 * @param brand
	 * @param model
	 * @param customName
	 * @param multiEngine
	 * @param multiPilot
	 */
	public AircraftModel( Integer userId, String brand, String model, String customName, Boolean multiEngine,
	        Boolean multiPilot ) {
		super();
		this.userId = userId;
		this.brand = brand;
		this.model = model;
		this.customName = customName;
		this.multiEngine = multiEngine;
		this.multiPilot = multiPilot;
	}

	@Override
	public String toString() {
		return "AircraftModel [id=" + id + ", userId=" + userId + ", brand=" + brand + ", model=" + model
		        + ", customName=" + customName + ", multiEngine=" + multiEngine + ", multiPilot=" + multiPilot + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( brand == null ) ? 0 : brand.hashCode() );
		result = prime * result + ( ( customName == null ) ? 0 : customName.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( model == null ) ? 0 : model.hashCode() );
		result = prime * result + ( ( multiEngine == null ) ? 0 : multiEngine.hashCode() );
		result = prime * result + ( ( multiPilot == null ) ? 0 : multiPilot.hashCode() );
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
		AircraftModel other = (AircraftModel) obj;
		if ( brand == null ) {
			if ( other.brand != null )
				return false;
		} else if ( !brand.equals( other.brand ) )
			return false;
		if ( customName == null ) {
			if ( other.customName != null )
				return false;
		} else if ( !customName.equals( other.customName ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( model == null ) {
			if ( other.model != null )
				return false;
		} else if ( !model.equals( other.model ) )
			return false;
		if ( multiEngine == null ) {
			if ( other.multiEngine != null )
				return false;
		} else if ( !multiEngine.equals( other.multiEngine ) )
			return false;
		if ( multiPilot == null ) {
			if ( other.multiPilot != null )
				return false;
		} else if ( !multiPilot.equals( other.multiPilot ) )
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

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand( String brand ) {
		this.brand = brand;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel( String model ) {
		this.model = model;
	}

	/**
	 * @return the customName
	 */
	public String getCustomName() {
		return customName;
	}

	/**
	 * @param customName
	 *            the customName to set
	 */
	public void setCustomName( String customName ) {
		this.customName = customName;
	}

	/**
	 * @return the multiEngine
	 */
	public Boolean getMultiEngine() {
		return multiEngine;
	}

	/**
	 * @param multiEngine
	 *            the multiEngine to set
	 */
	public void setMultiEngine( Boolean multiEngine ) {
		this.multiEngine = multiEngine;
	}

	/**
	 * @return the multiPilot
	 */
	public Boolean getMultiPilot() {
		return multiPilot;
	}

	/**
	 * @param multiPilot
	 *            the multiPilot to set
	 */
	public void setMultiPilot( Boolean multiPilot ) {
		this.multiPilot = multiPilot;
	}

}
