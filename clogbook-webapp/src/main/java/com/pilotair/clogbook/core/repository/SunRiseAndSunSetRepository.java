package com.pilotair.clogbook.core.repository;

import java.time.LocalDate;

import com.pilotair.clogbook.core.entity.srss.SrSsEntity;

public interface SunRiseAndSunSetRepository {

	SrSsEntity getSrSs( double latitude, double longitude, LocalDate localDate );

}
