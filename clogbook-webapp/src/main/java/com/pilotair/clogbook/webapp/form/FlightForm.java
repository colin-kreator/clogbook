package com.pilotair.clogbook.webapp.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.pilotair.clogbook.core.entity.Aircraft;

public class FlightForm {

	private LocalDate	date;

	private Aircraft	aircraft;

	@Override
	public String toString() {
		return "FlightForm [date=" + date + ", aircraft=" + aircraft + "]";
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate( String date ) {
		System.err.println( "IN DateSetter of FlightForm" );
		this.date = LocalDate.parse( date, DateTimeFormatter.ofPattern( "dd/MM/yy" ) );
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft( Aircraft aircraft ) {
		this.aircraft = aircraft;
	}

}
