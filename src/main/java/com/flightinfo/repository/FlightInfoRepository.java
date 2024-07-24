package com.flightinfo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightinfo.entity.FlightInfoEntity;

/**
 * The Interface FlightInfoRepository.
 */
@Repository
public interface FlightInfoRepository extends JpaRepository<FlightInfoEntity, Long>{

	/**
	 * Find by departure time between.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return the list
	 */
	List<FlightInfoEntity> findByDepartureTimeBetween(LocalDate fromDate, LocalDate toDate);

}
