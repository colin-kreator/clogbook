package com.pilotair.clogbook.core;

public enum StatType {

	TOTAL_HOURS( "TT", "Total time", "" ),
	SE_HOURS( "SE", "Single Engine time", "" ),
	ME_HOURS( "ME", "Multi Engine time", "" ),
	VFR_HOURS( "VFR", "VFR time", "" ),
	IFR_HOURS( "IFR", "IFR time", "" ),
	NIGHT_HOURS( "NGT", "Night time", "" ),
	MP_HOURS( "MP", "Multi Pilot time", "" ),
	TO_DAY( "TOD", "Day take offs", "" ),
	TO_NIGHT( "TON", "Night take offs", "" ),
	LDG_DAY( "LDD", "Day landings", "" ),
	LDG_NIGHT( "LDN", "Night landings", "" ),
	M3_HOURS( "3MH", "3 months hours", "" ),
	M6_HOURS( "6MH", "6 months hours", "" ),
	M12_HOURS( "12MH", "12 months hours", "" ),
	CM_HOURS( "CMH", "Current month hours", "" ),
	CY_HOURS( "CYH", "Current year hours", "" );

	private String	code;
	private String	alias;
	private String	description;

	private StatType( String code, String alias, String description ) {
		this.code = code;
		this.alias = alias;
		this.description = description;
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

}
