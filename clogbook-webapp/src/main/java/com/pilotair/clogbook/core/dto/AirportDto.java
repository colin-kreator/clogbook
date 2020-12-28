package com.pilotair.clogbook.core.dto;

public class AirportDto {

	private Integer	id;

	private String	name;

	private String	icaoCode;

	private String	iataCode;

	/**
	 * 
	 */
	public AirportDto() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param icaoCode
	 * @param iataCode
	 */
	public AirportDto( Integer id, String name, String icaoCode, String iataCode ) {
		super();
		this.id = id;
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
		AirportDto other = (AirportDto) obj;
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

	@Override
	public String toString() {
		return "AirportDto [id=" + id + ", name=" + name + ", icaoCode=" + icaoCode + ", iataCode=" + iataCode + "]";
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

}
