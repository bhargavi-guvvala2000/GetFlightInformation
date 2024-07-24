package com.flightinfo.vo;

import java.util.List;

import lombok.Data;

/**
 * Instantiates a new flight info response VO.
 */
@Data
public class FlightInfoResponseVO {

	/** The flights. */
	List<FlightInfoRSVO> flights;

	/**
	 * Gets the flights.
	 *
	 * @return the flights
	 */
	public List<FlightInfoRSVO> getFlights() {
		return flights;
	}

	/**
	 * Sets the flights.
	 *
	 * @param flights the flights to set
	 */
	public void setFlights(List<FlightInfoRSVO> flights) {
		this.flights = flights;
	}

}
