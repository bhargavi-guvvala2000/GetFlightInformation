package com.flightinfo.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightinfo.service.FlightInfoService;

import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class FlightInfoController.
 */
@RestController
@RequestMapping("/api/flightinfo")
public class FlightInfoController {

	/** The flight info service. */
	private FlightInfoService flightInfoService;

	/**
	 * Instantiates a new flight info controller.
	 *
	 * @param flightInfoService the flight info service
	 */
	@Autowired // Optional
	public FlightInfoController(FlightInfoService flightInfoService) {
		this.flightInfoService = flightInfoService;
	}

	/**
	 * Fetch and save.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/fetchAndSave")
	public ResponseEntity<String> fetchAndSave() {
		try {
			flightInfoService.fetchAndSaveFlightInfo();
			return ResponseEntity.ok("Flight information fetched and saved successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * Export exchange rates.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param format the format
	 * @param response the response
	 */
	@GetMapping("/export")
	public void exportExchangeRates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate, @RequestParam String format,
			HttpServletResponse response) {
		try {
			flightInfoService.exportFlights(fromDate, toDate, format, response);
		} catch (IllegalArgumentException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setContentType("text/plain");
			try {
				response.getWriter().write(e.getMessage());
			} catch (IOException ex) {
				throw new RuntimeException("Error writing response", ex);
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setContentType("text/plain");
			try {
				response.getWriter().write(e.getMessage());
			} catch (IOException ex) {
				throw new RuntimeException("Error writing response", ex);
			}
		}
	}
}
