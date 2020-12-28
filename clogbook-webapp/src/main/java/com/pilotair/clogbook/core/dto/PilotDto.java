package com.pilotair.clogbook.core.dto;

public class PilotDto {

	protected Integer	id;

	protected String	lastName;

	protected String	firstName;

	/**
	 * Bean Norm
	 */
	public PilotDto() {
		super();
	}

	/**
	 * @param id
	 * @param lastName
	 * @param firstName
	 */
	public PilotDto( Integer id, String lastName, String firstName ) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "PilotDto [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + "]";
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

}
