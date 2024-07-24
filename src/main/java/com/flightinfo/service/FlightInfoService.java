package com.flightinfo.service;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletResponse;

/**
 * The Interface FlightInfoService.
 */
public interface FlightInfoService {

	/**
	 * Fetch and save flight info.
	 */
	void fetchAndSaveFlightInfo();

	/**
	 * Export flights.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param format the format
	 * @param response the response
	 */
	void exportFlights(LocalDate fromDate, LocalDate toDate, String format, HttpServletResponse response);

}
