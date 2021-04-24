package com.pilotair.clogbook.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pilotair.clogbook.core.entity.Flight;

@Repository
public class FlightPagingRepositoryImpl implements FlightPagingRepository {

	@Autowired
	private EntityManager em;

	@Override
	public List<Flight> findFlightsForUser( Integer userId,
	        Integer offset, Integer size ) {

		String q = "SELECT f from Flight f where f.userId = :userId order by f.date desc";

		TypedQuery<Flight> query = em.createQuery( q, Flight.class );
		query.setParameter( "userId", userId );

		// Paginering
		query.setFirstResult( offset );
		query.setMaxResults( size );

		return query.getResultList();
	}

}
