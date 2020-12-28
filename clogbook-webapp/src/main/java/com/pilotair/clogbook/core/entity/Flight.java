package com.pilotair.clogbook.core.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table( name = "T_FLIGHT_FLT" )
public class Flight {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "flt_id" )
	private Integer		id;

	@Column( name = "flt_usr_id" )
	private Integer		userId;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "flt_act_id" )
	private Aircraft	aircraft;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "flt_plt_id" )
	private Pilot		pilot;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "flt_departure_apt_id" )
	private Airport		departureAirport;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "flt_arrival_apt_id" )
	private Airport		arrivalAirport;

	@Column( name = "flt_date", columnDefinition = "DATE" )
	private LocalDate	date;

	@Column( name = "flt_departure_time", columnDefinition = "TIME" )
	private LocalTime	departureTime;

	@Column( name = "flt_arrival_time", columnDefinition = "TIME" )
	private LocalTime	arrivalTime;

	@Column( name = "flt_day_to_nr" )
	private Short		dayTO;

	@Column( name = "flt_night_to_nr" )
	private Short		nightTO;

	@Column( name = "flt_day_ldg_nr" )
	private Short		dayLdg;

	@Column( name = "flt_night_ldg_nr" )
	private Short		nightLdg;

	@Column( name = "flt_multi_pilot" )
	private Boolean		multiPilot;

	@Column( name = "flt_multi_engine" )
	private Boolean		multiEngine;

	@Column( name = "flt_single_engine_time" )
	private Short		singleEngineTime;

	@Column( name = "flt_multi_engine_time" )
	private Short		multiEngineTime;

	@Column( name = "flt_night_time" )
	private Short		nightTime;

	@Column( name = "flt_instrument_time" )
	private Short		instrumentTime;

	@Column( name = "flt_total_time" )
	private Short		totalTime;

	@Column( name = "flt_pic_time" )
	private Short		picTime;

	@Column( name = "flt_multi_pilot_time" )
	private Short		multiPilotTime;

	@Column( name = "flt_copilot_time" )
	private Short		copilotTime;

	@Column( name = "flt_instructor_time" )
	private Short		instructorTime;

	@Column( name = "flt_dual_time" )
	private Short		dualTime;

	@Column( name = "flt_remarks" )
	private String		remarks;

	@Column( name = "flt_sim_type" )
	private String		simType;

	@Column( name = "flt_sim_time" )
	private Short		simTime;

	/**
	 * 
	 */
	public Flight() {
		super();
	}

	/**
	 * @param userId
	 * @param aircraft
	 * @param pilot
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param date
	 * @param departureTime
	 * @param arrivalTime
	 * @param dayTO
	 * @param nightTO
	 * @param dayLdg
	 * @param nightLdg
	 * @param multiPilot
	 * @param multiEngine
	 * @param singleEngineTime
	 * @param multiEngineTime
	 * @param nightTime
	 * @param instrumentTime
	 * @param totalTime
	 * @param picTime
	 * @param multiPilotTime
	 * @param copilotTime
	 * @param instructorTime
	 * @param dualTime
	 * @param remarks
	 * @param simType
	 * @param simTime
	 */
	public Flight( Integer userId, Aircraft aircraft, Pilot pilot, Airport departureAirport, Airport arrivalAirport,
	        LocalDate date, LocalTime departureTime, LocalTime arrivalTime, Short dayTO, Short nightTO, Short dayLdg,
	        Short nightLdg, Boolean multiPilot, Boolean multiEngine, Short singleEngineTime, Short multiEngineTime,
	        Short nightTime, Short instrumentTime, Short totalTime, Short picTime, Short multiPilotTime,
	        Short copilotTime, Short instructorTime, Short dualTime, String remarks, String simType, Short simTime ) {
		super();
		this.userId = userId;
		this.aircraft = aircraft;
		this.pilot = pilot;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.date = date;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.dayTO = dayTO;
		this.nightTO = nightTO;
		this.dayLdg = dayLdg;
		this.nightLdg = nightLdg;
		this.multiPilot = multiPilot;
		this.multiEngine = multiEngine;
		this.singleEngineTime = singleEngineTime;
		this.multiEngineTime = multiEngineTime;
		this.nightTime = nightTime;
		this.instrumentTime = instrumentTime;
		this.totalTime = totalTime;
		this.picTime = picTime;
		this.multiPilotTime = multiPilotTime;
		this.copilotTime = copilotTime;
		this.instructorTime = instructorTime;
		this.dualTime = dualTime;
		this.remarks = remarks;
		this.simType = simType;
		this.simTime = simTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( aircraft == null ) ? 0 : aircraft.hashCode() );
		result = prime * result + ( ( arrivalAirport == null ) ? 0 : arrivalAirport.hashCode() );
		result = prime * result + ( ( arrivalTime == null ) ? 0 : arrivalTime.hashCode() );
		result = prime * result + ( ( copilotTime == null ) ? 0 : copilotTime.hashCode() );
		result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
		result = prime * result + ( ( dayLdg == null ) ? 0 : dayLdg.hashCode() );
		result = prime * result + ( ( dayTO == null ) ? 0 : dayTO.hashCode() );
		result = prime * result + ( ( departureAirport == null ) ? 0 : departureAirport.hashCode() );
		result = prime * result + ( ( departureTime == null ) ? 0 : departureTime.hashCode() );
		result = prime * result + ( ( dualTime == null ) ? 0 : dualTime.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( instructorTime == null ) ? 0 : instructorTime.hashCode() );
		result = prime * result + ( ( instrumentTime == null ) ? 0 : instrumentTime.hashCode() );
		result = prime * result + ( ( multiEngine == null ) ? 0 : multiEngine.hashCode() );
		result = prime * result + ( ( multiEngineTime == null ) ? 0 : multiEngineTime.hashCode() );
		result = prime * result + ( ( multiPilot == null ) ? 0 : multiPilot.hashCode() );
		result = prime * result + ( ( multiPilotTime == null ) ? 0 : multiPilotTime.hashCode() );
		result = prime * result + ( ( nightLdg == null ) ? 0 : nightLdg.hashCode() );
		result = prime * result + ( ( nightTO == null ) ? 0 : nightTO.hashCode() );
		result = prime * result + ( ( nightTime == null ) ? 0 : nightTime.hashCode() );
		result = prime * result + ( ( picTime == null ) ? 0 : picTime.hashCode() );
		result = prime * result + ( ( pilot == null ) ? 0 : pilot.hashCode() );
		result = prime * result + ( ( remarks == null ) ? 0 : remarks.hashCode() );
		result = prime * result + ( ( simTime == null ) ? 0 : simTime.hashCode() );
		result = prime * result + ( ( simType == null ) ? 0 : simType.hashCode() );
		result = prime * result + ( ( singleEngineTime == null ) ? 0 : singleEngineTime.hashCode() );
		result = prime * result + ( ( totalTime == null ) ? 0 : totalTime.hashCode() );
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
		Flight other = (Flight) obj;
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
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
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
		if ( multiEngineTime == null ) {
			if ( other.multiEngineTime != null )
				return false;
		} else if ( !multiEngineTime.equals( other.multiEngineTime ) )
			return false;
		if ( multiPilot == null ) {
			if ( other.multiPilot != null )
				return false;
		} else if ( !multiPilot.equals( other.multiPilot ) )
			return false;
		if ( multiPilotTime == null ) {
			if ( other.multiPilotTime != null )
				return false;
		} else if ( !multiPilotTime.equals( other.multiPilotTime ) )
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
		if ( pilot == null ) {
			if ( other.pilot != null )
				return false;
		} else if ( !pilot.equals( other.pilot ) )
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
		if ( singleEngineTime == null ) {
			if ( other.singleEngineTime != null )
				return false;
		} else if ( !singleEngineTime.equals( other.singleEngineTime ) )
			return false;
		if ( totalTime == null ) {
			if ( other.totalTime != null )
				return false;
		} else if ( !totalTime.equals( other.totalTime ) )
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
	 * @return the pilot
	 */
	public Pilot getPilot() {
		return pilot;
	}

	/**
	 * @param pilot
	 *            the pilot to set
	 */
	public void setPilot( Pilot pilot ) {
		this.pilot = pilot;
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
	 * @return the singleEngineTime
	 */
	public Short getSingleEngineTime() {
		return singleEngineTime;
	}

	/**
	 * @param singleEngineTime
	 *            the singleEngineTime to set
	 */
	public void setSingleEngineTime( Short singleEngineTime ) {
		this.singleEngineTime = singleEngineTime;
	}

	/**
	 * @return the multiEngineTime
	 */
	public Short getMultiEngineTime() {
		return multiEngineTime;
	}

	/**
	 * @param multiEngineTime
	 *            the multiEngineTime to set
	 */
	public void setMultiEngineTime( Short multiEngineTime ) {
		this.multiEngineTime = multiEngineTime;
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
	 * @return the multiPilotTime
	 */
	public Short getMultiPilotTime() {
		return multiPilotTime;
	}

	/**
	 * @param multiPilotTime
	 *            the multiPilotTime to set
	 */
	public void setMultiPilotTime( Short multiPilotTime ) {
		this.multiPilotTime = multiPilotTime;
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

	@Override
	public String toString() {
		return "Flight [id=" + id + ", userId=" + userId + ", aircraft=" + aircraft + ", pilot=" + pilot
		        + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport + ", date=" + date
		        + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", dayTO=" + dayTO
		        + ", nightTO=" + nightTO + ", dayLdg=" + dayLdg + ", nightLdg=" + nightLdg + ", multiPilot="
		        + multiPilot + ", multiEngine=" + multiEngine + ", singleEngineTime=" + singleEngineTime
		        + ", multiEngineTime=" + multiEngineTime + ", nightTime=" + nightTime + ", instrumentTime="
		        + instrumentTime + ", totalTime=" + totalTime + ", picTime=" + picTime + ", multiPilotTime="
		        + multiPilotTime + ", copilotTime=" + copilotTime + ", instructorTime=" + instructorTime + ", dualTime="
		        + dualTime + ", remarks=" + remarks + ", simType=" + simType + ", simTime=" + simTime + "]";
	}

}
