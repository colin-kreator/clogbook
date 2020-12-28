package com.pilotair.clogbook.core.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.pilotair.clogbook.core.entity.srss.SrSsEntity;
import com.pilotair.clogbook.core.entity.srss.SrSsResultEntity;

@Repository
public class SunRiseAndSunSetRepositoryImpl implements SunRiseAndSunSetRepository {

	private static final String	URL_API	= "https://api.sunrise-sunset.org/json?";	// lat=36.7201600&lng=-4.4203400&date=2020-12-27";
	@Autowired
	private RestTemplate		restTemplate;

	@Override
	public SrSsEntity getSrSs( double latitude, double longitude, LocalDate localDate ) {

		String url = URL_API + "lat=" + latitude + "&lng=" + longitude + "&date="
		        + localDate.format( DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) );

		SrSsResultEntity reponse = restTemplate.getForEntity( url, SrSsResultEntity.class )
		        .getBody();

		if ( reponse == null )
			return null;

		return reponse.getSrSsEntity();
	}

}
