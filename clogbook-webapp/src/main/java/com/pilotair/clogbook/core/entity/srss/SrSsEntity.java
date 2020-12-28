package com.pilotair.clogbook.core.entity.srss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class SrSsEntity {
	@JsonProperty( value = "sunrise" )
	private String	sunRise;

	@JsonProperty( value = "sunset" )
	private String	sunSet;

	/**
	 * @return the sunRise
	 */
	public String getSunRise() {
		return sunRise;
	}

	/**
	 * @param sunRise
	 *            the sunRise to set
	 */
	public void setSunRise( String sunRise ) {
		this.sunRise = sunRise;
	}

	/**
	 * @return the sunSet
	 */
	public String getSunSet() {
		return sunSet;
	}

	/**
	 * @param sunSet
	 *            the sunSet to set
	 */
	public void setSunSet( String sunSet ) {
		this.sunSet = sunSet;
	}

}
