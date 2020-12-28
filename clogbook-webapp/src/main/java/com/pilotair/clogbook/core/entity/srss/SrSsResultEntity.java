package com.pilotair.clogbook.core.entity.srss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class SrSsResultEntity {

	@JsonProperty( "results" )
	private SrSsEntity srSsEntity;

	/**
	 * @return the srSsEntity
	 */
	public SrSsEntity getSrSsEntity() {
		return srSsEntity;
	}

	/**
	 * @param srSsEntity
	 *            the srSsEntity to set
	 */
	public void setSrSsEntity( SrSsEntity srSsEntity ) {
		this.srSsEntity = srSsEntity;
	}

}
