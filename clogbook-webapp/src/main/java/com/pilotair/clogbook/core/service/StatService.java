package com.pilotair.clogbook.core.service;

import com.pilotair.clogbook.core.StatType;

public interface StatService {

	String[] getCounterStat( StatType statType, Integer userId );

}
