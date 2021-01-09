package com.pilotair.clogbook.webapp.form;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pilotair.clogbook.core.dto.PilotDto;
import com.pilotair.clogbook.core.entity.Aircraft;
import com.pilotair.clogbook.core.entity.Airport;
import com.pilotair.clogbook.core.entity.Flight;

public class FlightForm {

	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy" )
	private LocalDate	date;

	private Aircraft	aircraft;

	private Airport		departureAirport;

	private Airport		arrivalAirport;

	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "HH:mm" )
	private LocalTime	departureTime;

	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "HH:mm" )
	private LocalTime	arrivalTime;

	private PilotDto	pilotDto;

	private Short		dayTO;

	private Short		nightTO;

	private Short		dayLdg;

	private Short		nightLdg;

	private Boolean		multiPilot;

	private Boolean		ifrFlight;

	private Boolean		multiEngine;

	private Short		totalTime;
	private Short		nightTime;
	private Short		instrumentTime;
	private Short		crossCountryTime;
	private Short		picTime;
	private Short		dualTime;
	private Short		copilotTime;
	private Short		instructorTime;

	private String		remarks;

	private String		simType;
	private Short		simTime;

	public Flight createFlight() {
		Flight flight;

		Short singleEngineTime = null;
		Short multiEngineTime = null;
		Short multiPilotTime = null;

		if ( multiEngine != null && multiEngine )
			multiEngineTime = totalTime;
		else if ( multiEngine != null )
			singleEngineTime = totalTime;

		if ( multiPilot != null && multiPilot )
			multiPilotTime = totalTime;

		flight = new Flight( null, aircraft, null, departureAirport, arrivalAirport, date, departureTime,
		        arrivalTime, dayTO, nightTO, dayLdg, nightLdg, multiPilot, multiEngine, singleEngineTime,
		        multiEngineTime, nightTime, instrumentTime, totalTime, picTime, multiPilotTime, copilotTime,
		        instructorTime, dualTime, remarks, simType, simTime, crossCountryTime );

		flight.setPilotDto( pilotDto );
		return flight;
	}

	@Override
	public String toString() {
		return "FlightForm [date=" + date + ", aircraft=" + aircraft + ", departureAirport=" + departureAirport
		        + ", arrivalAirport=" + arrivalAirport + ", departureTime=" + departureTime + ", arrivalTime="
		        + arrivalTime + ", pilotDto=" + pilotDto + ", dayTO=" + dayTO + ", nightTO=" + nightTO + ", dayLdg="
		        + dayLdg
		        + ", nightLdg=" + nightLdg + ", multiPilot=" + multiPilot + ", ifrFlight=" + ifrFlight
		        + ", multiEngine=" + multiEngine + ", totalTime=" + totalTime + ", nightTime=" + nightTime
		        + ", instrumentTime=" + instrumentTime + ", crossCountryTime=" + crossCountryTime + ", picTime="
		        + picTime + ", dualTime=" + dualTime + ", copilotTime=" + copilotTime + ", instructorTime="
		        + instructorTime + ", remarks=" + remarks + ", simType=" + simType + ", simTime=" + simTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( aircraft == null ) ? 0 : aircraft.hashCode() );
		result = prime * result + ( ( arrivalAirport == null ) ? 0 : arrivalAirport.hashCode() );
		result = prime * result + ( ( arrivalTime == null ) ? 0 : arrivalTime.hashCode() );
		result = prime * result + ( ( copilotTime == null ) ? 0 : copilotTime.hashCode() );
		result = prime * result + ( ( crossCountryTime == null ) ? 0 : crossCountryTime.hashCode() );
		result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
		result = prime * result + ( ( dayLdg == null ) ? 0 : dayLdg.hashCode() );
		result = prime * result + ( ( dayTO == null ) ? 0 : dayTO.hashCode() );
		result = prime * result + ( ( departureAirport == null ) ? 0 : departureAirport.hashCode() );
		result = prime * result + ( ( departureTime == null ) ? 0 : departureTime.hashCode() );
		result = prime * result + ( ( dualTime == null ) ? 0 : dualTime.hashCode() );
		result = prime * result + ( ( ifrFlight == null ) ? 0 : ifrFlight.hashCode() );
		result = prime * result + ( ( instructorTime == null ) ? 0 : instructorTime.hashCode() );
		result = prime * result + ( ( instrumentTime == null ) ? 0 : instrumentTime.hashCode() );
		result = prime * result + ( ( multiEngine == null ) ? 0 : multiEngine.hashCode() );
		result = prime * result + ( ( multiPilot == null ) ? 0 : multiPilot.hashCode() );
		result = prime * result + ( ( nightLdg == null ) ? 0 : nightLdg.hashCode() );
		result = prime * result + ( ( nightTO == null ) ? 0 : nightTO.hashCode() );
		result = prime * result + ( ( nightTime == null ) ? 0 : nightTime.hashCode() );
		result = prime * result + ( ( picTime == null ) ? 0 : picTime.hashCode() );
		result = prime * result + ( ( pilotDto == null ) ? 0 : pilotDto.hashCode() );
		result = prime * result + ( ( remarks == null ) ? 0 : remarks.hashCode() );
		result = prime * result + ( ( simTime == null ) ? 0 : simTime.hashCode() );
		result = prime * result + ( ( simType == null ) ? 0 : simType.hashCode() );
		result = prime * result + ( ( totalTime == null ) ? 0 : totalTime.hashCode() );
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
		FlightForm other = (FlightForm) obj;
		if ( aircraft == null ) {
			if ( other.aircraft != null )
				return false;
		} else if ( !aircraft.equals( other.aircraft ) )
			return false;
		if ( arrivalAirport == null ) {
			if ( other.arrivalAirport != null )
				return false;
		} else if ( !arrivalAirport.equals( other.arrivalAirport ) )
			return false;
		if ( arrivalTime == null ) {
			if ( other.arrivalTime != null )
				return false;
		} else if ( !arrivalTime.equals( other.arrivalTime ) )
			return false;
		if ( copilotTime == null ) {
			if ( other.copilotTime != null )
				return false;
		} else if ( !copilotTime.equals( other.copilotTime ) )
			return false;
		if ( crossCountryTime == null ) {
			if ( other.crossCountryTime != null )
				return false;
		} else if ( !crossCountryTime.equals( other.crossCountryTime ) )
			return false;
		if ( date == null ) {
			if ( other.date != null )
				return false;
		} else if ( !date.equals( other.date ) )
			return false;
		if ( dayLdg == null ) {
			if ( other.dayLdg != null )
				return false;
		} else if ( !dayLdg.equals( other.dayLdg ) )
			return false;
		if ( dayTO == null ) {
			if ( other.dayTO != null )
				return false;
		} else if ( !dayTO.equals( other.dayTO ) )
			return false;
		if ( departureAirport == null ) {
			if ( other.departureAirport != null )
				return false;
		} else if ( !departureAirport.equals( other.departureAirport ) )
			return false;
		if ( departureTime == null ) {
			if ( other.departureTime != null )
				return false;
		} else if ( !departureTime.equals( other.departureTime ) )
			return false;
		if ( dualTime == null ) {
			if ( other.dualTime != null )
				return false;
		} else if ( !dualTime.equals( other.dualTime ) )
			return false;
		if ( ifrFlight == null ) {
			if ( other.ifrFlight != null )
				return false;
		} else if ( !ifrFlight.equals( other.ifrFlight ) )
			return false;
		if ( instructorTime == null ) {
			if ( other.instructorTime != null )
				return false;
		} else if ( !instructorTime.equals( other.instructorTime ) )
			return false;
		if ( instrumentTime == null ) {
			if ( other.instrumentTime != null )
				return false;
		} else if ( !instrumentTime.equals( other.instrumentTime ) )
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
		if ( nightLdg == null ) {
			if ( other.nightLdg != null )
				return false;
		} else if ( !nightLdg.equals( other.nightLdg ) )
			return false;
		if ( nightTO == null ) {
			if ( other.nightTO != null )
				return false;
		} else if ( !nightTO.equals( other.nightTO ) )
			return false;
		if ( nightTime == null ) {
			if ( other.nightTime != null )
				return false;
		} else if ( !nightTime.equals( other.nightTime ) )
			return false;
		if ( picTime == null ) {
			if ( other.picTime != null )
				return false;
		} else if ( !picTime.equals( other.picTime ) )
			return false;
		if ( pilotDto == null ) {
			if ( other.pilotDto != null )
				return false;
		} else if ( !pilotDto.equals( other.pilotDto ) )
			return false;
		if ( remarks == null ) {
			if ( other.remarks != null )
				return false;
		} else if ( !remarks.equals( other.remarks ) )
			return false;
		if ( simTime == null ) {
			if ( other.simTime != null )
				return false;
		} else if ( !simTime.equals( other.simTime ) )
			return false;
		if ( simType == null ) {
			if ( other.simType != null )
				return false;
		} else if ( !simType.equals( other.simType ) )
			return false;
		if ( totalTime == null ) {
			if ( other.totalTime != null )
				return false;
		} else if ( !totalTime.equals( other.totalTime ) )
			return false;
		return true;
	}

	/**
	 * @return the simType
	 */
	public String getSimType() {
		return simType;
	}

	/**
	 * @param simType
	 *            the simType to set
	 */
	public void setSimType( String simType ) {
		this.simType = simType;
	}

	/**
	 * @return the simTime
	 */
	public Short getSimTime() {
		return simTime;
	}

	/**
	 * @param simTime
	 *            the simTime to set
	 */
	public void setSimTime( Short simTime ) {
		this.simTime = simTime;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate( LocalDate date ) {
		this.date = date;
	}

	/**
	 * @return the aircraft
	 */
	public Aircraft getAircraft() {
		return aircraft;
	}

	/**
	 * @param aircraft
	 *            the aircraft to set
	 */
	public void setAircraft( Aircraft aircraft ) {
		this.aircraft = aircraft;
	}

	/**
	 * @return the departureAirport
	 */
	public Airport getDepartureAirport() {
		return departureAirport;
	}

	/**
	 * @param departureAirport
	 *            the departureAirport to set
	 */
	public void setDepartureAirport( Airport departureAirport ) {
		this.departureAirport = departureAirport;
	}

	/**
	 * @return the arrivalAirport
	 */
	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	/**
	 * @param arrivalAirport
	 *            the arrivalAirport to set
	 */
	public void setArrivalAirport( Airport arrivalAirport ) {
		this.arrivalAirport = arrivalAirport;
	}

	/**
	 * @return the departureTime
	 */
	public LocalTime getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime
	 *            the departureTime to set
	 */
	public void setDepartureTime( LocalTime departureTime ) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime
	 *            the arrivalTime to set
	 */
	public void setArrivalTime( LocalTime arrivalTime ) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the pilot
	 */
	public PilotDto getPilotDto() {
		return pilotDto;
	}

	/**
	 * @param pilot
	 *            the pilot to set
	 */
	public void setPilotDto( PilotDto pilotDto ) {
		this.pilotDto = pilotDto;
	}

	/**
	 * @return the dayTO
	 */
	public Short getDayTO() {
		return dayTO;
	}

	/**
	 * @param dayTO
	 *            the dayTO to set
	 */
	public void setDayTO( Short dayTO ) {
		this.dayTO = dayTO;
	}

	/**
	 * @return the nightTO
	 */
	public Short getNightTO() {
		return nightTO;
	}

	/**
	 * @param nightTO
	 *            the nightTO to set
	 */
	public void setNightTO( Short nightTO ) {
		this.nightTO = nightTO;
	}

	/**
	 * @return the dayLdg
	 */
	public Short getDayLdg() {
		return dayLdg;
	}

	/**
	 * @param dayLdg
	 *            the dayLdg to set
	 */
	public void setDayLdg( Short dayLdg ) {
		this.dayLdg = dayLdg;
	}

	/**
	 * @return the nightLdg
	 */
	public Short getNightLdg() {
		return nightLdg;
	}

	/**
	 * @param nightLdg
	 *            the nightLdg to set
	 */
	public void setNightLdg( Short nightLdg ) {
		this.nightLdg = nightLdg;
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

	/**
	 * @return the ifrFlight
	 */
	public Boolean getIfrFlight() {
		return ifrFlight;
	}

	/**
	 * @param ifrFlight
	 *            the ifrFlight to set
	 */
	public void setIfrFlight( Boolean ifrFlight ) {
		this.ifrFlight = ifrFlight;
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
	 * @return the totalTime
	 */
	public Short getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime
	 *            the totalTime to set
	 */
	public void setTotalTime( Short totalTime ) {
		this.totalTime = totalTime;
	}

	/**
	 * @return the nightTime
	 */
	public Short getNightTime() {
		return nightTime;
	}

	/**
	 * @param nightTime
	 *            the nightTime to set
	 */
	public void setNightTime( Short nightTime ) {
		this.nightTime = nightTime;
	}

	/**
	 * @return the instrumentTime
	 */
	public Short getInstrumentTime() {
		return instrumentTime;
	}

	/**
	 * @param instrumentTime
	 *            the instrumentTime to set
	 */
	public void setInstrumentTime( Short instrumentTime ) {
		this.instrumentTime = instrumentTime;
	}

	/**
	 * @return the crossCountryTime
	 */
	public Short getCrossCountryTime() {
		return crossCountryTime;
	}

	/**
	 * @param crossCountryTime
	 *            the crossCountryTime to set
	 */
	public void setCrossCountryTime( Short crossCountryTime ) {
		this.crossCountryTime = crossCountryTime;
	}

	/**
	 * @return the picTime
	 */
	public Short getPicTime() {
		return picTime;
	}

	/**
	 * @param picTime
	 *            the picTime to set
	 */
	public void setPicTime( Short picTime ) {
		this.picTime = picTime;
	}

	/**
	 * @return the dualTime
	 */
	public Short getDualTime() {
		return dualTime;
	}

	/**
	 * @param dualTime
	 *            the dualTime to set
	 */
	public void setDualTime( Short dualTime ) {
		this.dualTime = dualTime;
	}

	/**
	 * @return the copilotTime
	 */
	public Short getCopilotTime() {
		return copilotTime;
	}

	/**
	 * @param copilotTime
	 *            the copilotTime to set
	 */
	public void setCopilotTime( Short copilotTime ) {
		this.copilotTime = copilotTime;
	}

	/**
	 * @return the instructorTime
	 */
	public Short getInstructorTime() {
		return instructorTime;
	}

	/**
	 * @param instructorTime
	 *            the instructorTime to set
	 */
	public void setInstructorTime( Short instructorTime ) {
		this.instructorTime = instructorTime;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks( String remarks ) {
		this.remarks = remarks;
	}

}
