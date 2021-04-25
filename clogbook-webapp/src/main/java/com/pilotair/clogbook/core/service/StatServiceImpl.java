package com.pilotair.clogbook.core.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilotair.clogbook.core.StatType;
import com.pilotair.clogbook.core.entity.Flight;
import com.pilotair.clogbook.core.repository.FlightRepository;

@Service
public class StatServiceImpl implements StatService {

	// TODO A COMPLETER

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public String[] getCounterStat( StatType stat, Integer userId ) {

		List<Flight> flights = null;

		if ( stat.getTimeRange().equalsIgnoreCase( "TOTAL" ) ) {
			flights = flightRepository.findByUserId( userId );
		} else if ( stat.getTimeRange().equalsIgnoreCase( "AFTER" ) ) {
			LocalDate date = LocalDate.now();
			flights = flightRepository.findByUserIdAndDateAfter( userId, date );
		} else if ( stat.getTimeRange().equalsIgnoreCase( "BEFORE" ) ) {
			LocalDate date = LocalDate.now();
			flights = flightRepository.findByUserIdAndDateBefore( userId, date );
		} else if ( stat.getTimeRange().equalsIgnoreCase( "RANGE" ) ) {
			LocalDate dateAfter = LocalDate.now();
			LocalDate dateBefore = LocalDate.now();
			flights = flightRepository.findByUserIdAndDateBetween( userId, dateAfter, dateBefore );
		}
		if ( flights == null )
			return new String[] { "Err", "or" };
		if ( stat.getType().equalsIgnoreCase( "TIME" ) ) {
			Long time = 0L;
			if ( stat == StatType.TOTAL_HOURS ) {
				for ( Flight flt : flights )
					if ( flt.getTotalTime() != null )
						time += flt.getTotalTime();
			}
			if ( stat == StatType.SE_HOURS ) {
				for ( Flight flt : flights )
					if ( flt.getTotalTime() != null &&
					        flt.getMultiEngine() != null
					        && !flt.getMultiEngine() )
						time += flt.getTotalTime();
			}
			if ( stat == StatType.ME_HOURS ) {
				for ( Flight flt : flights )
					if ( flt.getTotalTime() != null &&
					        flt.getMultiEngine() != null
					        && flt.getMultiEngine() )
						time += flt.getTotalTime();
			}
			if ( stat == StatType.VFR_HOURS ) {
				Long instrum = 0L;
				for ( Flight flt : flights ) {
					if ( flt.getInstrumentTime() != null )
						instrum += flt.getInstrumentTime();
					if ( flt.getTotalTime() != null )
						time += flt.getTotalTime();
				}
				time -= instrum;
			}
			if ( stat == StatType.IFR_HOURS ) {
				for ( Flight flt : flights ) {
					if ( flt.getInstrumentTime() != null )
						time += flt.getInstrumentTime();

				}

			}
			if ( stat == StatType.NIGHT_HOURS ) {
				for ( Flight flt : flights ) {
					if ( flt.getNightTime() != null )
						time += flt.getNightTime();

				}

			}

			if ( time != null ) {
				String hours = (int) ( ( time - time % 60 ) / 60 ) + "";
				String minutes = String.format( "%02d", ( time % 60 ) );

				if ( hours.length() > 3 ) {
					String a = hours.substring( 0, hours.length() - 3 );
					String b = hours.substring( hours.length() - 3 );
					hours = a + " " + b;
				}

				return new String[] { hours, ":" + minutes };
			}

		} else if ( stat.getType().equalsIgnoreCase( "INT" ) ) {
			return new String[] { "12", null };
		}

		return new String[] { "Err", "or" };
	}

}
