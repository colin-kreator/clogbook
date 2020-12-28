package com.pilotair.clogbook.webapp.form;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.Airport;

public class FlightForm {

	private LocalDate	date;

	private Aircraft	aircraft;

	private Airport		departureAirport;

	private Airport		arrivalAirport;

	private LocalTime	departureTime;

	private LocalTime	arrivalTime;

	@Override
	public String toString() {
		return "FlightForm [date=" + date + ", aircraft=" + aircraft + ", departureAirport=" + departureAirport
		        + ", arrivalAirport=" + arrivalAirport + ", departureTime=" + departureTime + ", arrivalTime="
		        + arrivalTime + "]";
	}

	private LocalTime parseToLocalTime( String str ) {
		return LocalTime.parse( str, DateTimeFormatter.ofPattern( "HH:mm" ) );
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

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport( Airport departureAirport ) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport( Airport arrivalAirport ) {
		this.arrivalAirport = arrivalAirport;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime( String departureTime ) {
		this.departureTime = parseToLocalTime( departureTime );
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime( String arrivalTime ) {
		this.arrivalTime = parseToLocalTime( arrivalTime );
	}

}
