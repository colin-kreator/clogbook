package com.pilotair.clogbook.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatType {

	TOTAL_HOURS( "TT", "Total time", "Total flight time", "TIME", "TOTAL" ),
	SE_HOURS( "SE", "Single Engine time", "", "TIME", "TOTAL" ),
	ME_HOURS( "ME", "Multi Engine time", "", "TIME", "TOTAL" ),
	VFR_HOURS( "VFR", "VFR time", "", "TIME", "TOTAL" ),
	IFR_HOURS( "IFR", "IFR time", "", "TIME", "TOTAL" ),
	NIGHT_HOURS( "NGT", "Night time", "", "TIME", "TOTAL" ),
	MP_HOURS( "MP", "Multi Pilot time", "", "TIME", "TOTAL" ),
	TO_DAY( "TOD", "Day take offs", "", "INT", "TOTAL" ),
	TO_NIGHT( "TON", "Night take offs", "", "INT", "TOTAL" ),
	LDG_DAY( "LDD", "Day landings", "", "INT", "TOTAL" ),
	LDG_NIGHT( "LDN", "Night landings", "", "INT", "TOTAL" ),
	M3_HOURS( "3MH", "3 months hours", "", "TIME", "AFTER" ),
	M6_HOURS( "6MH", "6 months hours", "", "TIME", "AFTER" ),
	M12_HOURS( "12MH", "12 months hours", "", "TIME", "AFTER" ),
	CM_HOURS( "CMH", "Current month hours", "", "TIME", "AFTER" ),
	CY_HOURS( "CYH", "Current year hours", "", "TIME", "AFTER" );

	@JsonProperty
	private String	code;

	private String	alias;

	private String	description;

	private String	type;

	private String	timeRange;

	private StatType( String code, String alias, String description, String type, String timeRange ) {
		this.code = code;
		this.alias = alias;
		this.description = description;
		this.timeRange = timeRange;
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the timeRange
	 */
	public String getTimeRange() {
		return timeRange;
	}

}
