package com.flightinfo.vo;

import java.time.LocalDate;

/**
 * The Class FlightInfoRSVO.
 */
public class FlightInfoRSVO {
	
	/** The flight no. */
	private String flight_no;
	
	/** The origin. */
	private String origin;
	
	/** The departure time. */
	private LocalDate departure_time;
	
	/** The destination. */
	private String destination;
	
	/** The arrival time. */
	private LocalDate arrival_time;
	
	/** The airline. */
	private String airline;
	
	/** The gate. */
	private String gate;
	
	/** The status. */
	private String status;

	/**
	 * Gets the flight no.
	 *
	 * @return the flight_no
	 */
	public String getFlight_no() {
		return flight_no;
	}

	/**
	 * Sets the flight no.
	 *
	 * @param flight_no the flight_no to set
	 */
	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
	}

	/**
	 * Gets the origin.
	 *
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Sets the origin.
	 *
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Gets the departure time.
	 *
	 * @return the departure_time
	 */
	public LocalDate getDeparture_time() {
		return departure_time;
	}

	/**
	 * Sets the departure time.
	 *
	 * @param departure_time the departure_time to set
	 */
	public void setDeparture_time(LocalDate departure_time) {
		this.departure_time = departure_time;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the destination.
	 *
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Gets the arrival time.
	 *
	 * @return the arrival_time
	 */
	public LocalDate getArrival_time() {
		return arrival_time;
	}

	/**
	 * Sets the arrival time.
	 *
	 * @param arrival_time the arrival_time to set
	 */
	public void setArrival_time(LocalDate arrival_time) {
		this.arrival_time = arrival_time;
	}

	/**
	 * Gets the airline.
	 *
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Sets the airline.
	 *
	 * @param airline the airline to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * Gets the gate.
	 *
	 * @return the gate
	 */
	public String getGate() {
		return gate;
	}

	/**
	 * Sets the gate.
	 *
	 * @param gate the gate to set
	 */
	public void setGate(String gate) {
		this.gate = gate;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FlightInfoRSVO [flight_no=" + flight_no + ", origin=" + origin + ", departure_time=" + departure_time
				+ ", destination=" + destination + ", arrival_time=" + arrival_time + ", airline=" + airline + ", gate="
				+ gate + ", status=" + status + "]";
	}

}
