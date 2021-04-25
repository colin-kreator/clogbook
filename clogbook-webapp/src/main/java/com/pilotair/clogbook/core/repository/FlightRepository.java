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

	List<Flight> findByUserIdOrderByDateDescDepartureTimeDesc( Integer userId );

	List<Flight> findTop20ByUserIdOrderByDateDescDepartureTimeDesc( Integer userId );

	/*Permet d'avoir la liste de simulateurs utilisés par l'utilisateur entre les 2 dates données*/
	@Query( "select distinct f.simType from Flight f where f.userId = :userId and f.simType is not null and f.date between :datestart and :dateend order by f.simType" )
	List<String> findDistinctSimTypeByUserId( @Param( value = "userId" ) Integer userId,
	        @Param( value = "datestart" ) LocalDate dateStart,
	        @Param( value = "dateend" ) LocalDate dateEnd );

	void deleteByUserId( Integer userId );

	List<Flight> findByUserId( Integer userId );

	List<Flight> findByUserIdAndDateAfter( Integer userId, LocalDate date );

	List<Flight> findByUserIdAndDateBetween( Integer userId, LocalDate dateAfter, LocalDate dateBefore );

	List<Flight> findByUserIdAndDateBefore( Integer userId, LocalDate date );
}
