package com.pilotair.clogbook.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "TR_AIRPORT_APT" )
public class Airport {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "apt_id" )
	private Integer	id;

	@Column( name = "apt_name" )
	private String	name;

	@Column( name = "apt_icao_code" )
	private String	icaoCode;

	@Column( name = "apt_iata_code" )
	private String	iataCode;

	@Column( name = "apt_latitude" )
	private Double	latitude;

	@Column( name = "apt_longitude" )
	private Double	longitude;

	/**
	 * 
	 */
	public Airport() {
		super();
	}

	/**
	 * @param name
	 * @param icaoCode
	 * @param iataCode
	 */
	public Airport( String name, String icaoCode, String iataCode ) {
		super();
		this.name = name;
		this.icaoCode = icaoCode;
		this.iataCode = iataCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( iataCode == null ) ? 0 : iataCode.hashCode() );
		result = prime * result + ( ( icaoCode == null ) ? 0 : icaoCode.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
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
		Airport other = (Airport) obj;
		if ( iataCode == null ) {
			if ( other.iataCode != null )
				return false;
		} else if ( !iataCode.equals( other.iataCode ) )
			return false;
		if ( icaoCode == null ) {
			if ( other.icaoCode != null )
				return false;
		} else if ( !icaoCode.equals( other.icaoCode ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the icaoCode
	 */
	public String getIcaoCode() {
		return icaoCode;
	}

	/**
	 * @param icaoCode
	 *            the icaoCode to set
	 */
	public void setIcaoCode( String icaoCode ) {
		this.icaoCode = icaoCode;
	}

	/**
	 * @return the iataCode
	 */
	public String getIataCode() {
		return iataCode;
	}

	/**
	 * @param iataCode
	 *            the iataCode to set
	 */
	public void setIataCode( String iataCode ) {
		this.iataCode = iataCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude( Double latitude ) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude( Double longitude ) {
		this.longitude = longitude;
	}

}
