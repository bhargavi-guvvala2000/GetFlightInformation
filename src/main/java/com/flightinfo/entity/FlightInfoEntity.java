package com.flightinfo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Class FlightInfoEntity.
 */
@Entity
@Table(name="Flight_Data")
public class FlightInfoEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The flight no. */
	@Column(name = "FLT_NO")
	private String flightNo;

	/** The origin. */
	@Column(name = "ORIGIN")
	private String origin;

	/** The departure time. */
	@Column(name = "DEP_TIME")
	private LocalDate departureTime;

	/** The destination. */
	@Column(name = "DESTINATION")
	private String destination;

	/** The arrival time. */
	@Column(name = "ARR_TIME")
	private LocalDate arrivalTime;

	/** The airline. */
	@Column(name = "AIRLINE_NAME")
	private String airline;

	/** The gate. */
	@Column(name = "GATE_NO")
	private String gate;

	/** The status. */
	@Column(name = "FLT_STATUS")
	private String status;

	/**
	 * Instantiates a new flight info entity.
	 */
	public FlightInfoEntity() {

	}

	/**
	 * Instantiates a new flight info entity.
	 *
	 * @param flightNo the flight no
	 * @param origin the origin
	 * @param departureTime the departure time
	 * @param destination the destination
	 * @param arrivalTime the arrival time
	 * @param airline the airline
	 * @param gate the gate
	 * @param status the status
	 */
	public FlightInfoEntity(String flightNo, String origin, LocalDate departureTime, String destination,
			LocalDate arrivalTime, String airline, String gate, String status) {
		super();
		this.flightNo = flightNo;
		this.origin = origin;
		this.departureTime = departureTime;
		this.destination = destination;
		this.arrivalTime = arrivalTime;
		this.airline = airline;
		this.gate = gate;
		this.status = status;
	}

	/**
	 * Gets the flight no.
	 *
	 * @return the flightNo
	 */
	public String getFlightNo() {
		return flightNo;
	}

	/**
	 * Sets the flight no.
	 *
	 * @param flightNo the flightNo to set
	 */
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
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
	 * @return the departureTime
	 */
	public LocalDate getDepartureTime() {
		return departureTime;
	}

	/**
	 * Sets the departure time.
	 *
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(LocalDate departureTime) {
		this.departureTime = departureTime;
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
	 * @return the arrivalTime
	 */
	public LocalDate getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the arrival time.
	 *
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(LocalDate arrivalTime) {
		this.arrivalTime = arrivalTime;
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

}
