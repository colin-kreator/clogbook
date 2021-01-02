package com.pilotair.clogbook.core.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	List<Flight> findByUserIdOrderByDateDesc( Integer userId );

	@Query( "select distinct f.simType from Flight f where f.userId = :userId and f.simType is not null and f.date between :datestart and :dateend order by f.simType" )
	List<String> findDistinctSimTypeByUserId( @Param( value = "userId" ) Integer userId,
	        @Param( value = "datestart" ) LocalDate dateStart,
	        @Param( value = "dateend" ) LocalDate dateEnd );
}
