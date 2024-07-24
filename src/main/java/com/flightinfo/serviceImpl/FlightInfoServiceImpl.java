package com.flightinfo.serviceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flightinfo.entity.FlightInfoEntity;
import com.flightinfo.repository.FlightInfoRepository;
import com.flightinfo.service.FlightInfoService;
import com.flightinfo.vo.FlightInfoRSVO;
import com.flightinfo.vo.FlightInfoResponseVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;

import jakarta.servlet.http.HttpServletResponse;

/**
 * The Class FlightInfoServiceImpl.
 */
@Service
public class FlightInfoServiceImpl implements FlightInfoService {

	/** The url. */
	@Value("${flight.info.api.url}")
	private String url;

	/** The flight info repository. */
	@Autowired
	private FlightInfoRepository flightInfoRepository;

	/** The rest template. */
	private final RestTemplate restTemplate = new RestTemplate();

	/**
	 * Fetch and save flight info.
	 */
	@Override
	public void fetchAndSaveFlightInfo() {

		try {
			FlightInfoResponseVO response = restTemplate.getForObject(url, FlightInfoResponseVO.class);
			if (response != null && response.getFlights() != null) {
				for (FlightInfoRSVO responseVO : response.getFlights()) {
					FlightInfoEntity flightInfoEntity = new FlightInfoEntity();
					flightInfoEntity.setFlightNo(responseVO.getFlight_no());
					flightInfoEntity.setOrigin(responseVO.getOrigin());
					flightInfoEntity.setDepartureTime(responseVO.getDeparture_time());
					flightInfoEntity.setDestination(responseVO.getDestination());
					flightInfoEntity.setArrivalTime(responseVO.getArrival_time());
					flightInfoEntity.setAirline(responseVO.getAirline());
					flightInfoEntity.setGate(responseVO.getGate());
					flightInfoEntity.setStatus(responseVO.getStatus());
					flightInfoRepository.save(flightInfoEntity);

				}
			} else {
				throw new RuntimeException("Invalid API response or no flights found.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch and save flight information: " + e.getMessage());
		}

	}

	/**
	 * Export flights.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param format the format
	 * @param response the response
	 */
	@Override
	public void exportFlights(LocalDate fromDate, LocalDate toDate, String format, HttpServletResponse response) {
		try {
			List<FlightInfoEntity> flights = getFlightInfo(fromDate, toDate);
			switch (format.toLowerCase()) {
			case "csv":
				exportFlightsToCsv(flights, response);
				break;
			case "pdf":
				exportFlightsToPdf(flights, response);
				break;
			case "json":
				exportToJson(flights, response);
				break;
			default:
				throw new IllegalArgumentException("Unsupported format: " + format);
			}
		} catch (IllegalArgumentException e) {
			throw e; // Let the controller handle this specific exception
		} catch (Exception e) {
			throw new RuntimeException("Failed to export flights: " + e.getMessage());
		}
	}

	/**
	 * Export to json.
	 *
	 * @param flights the flights
	 * @param response the response
	 */
	private void exportToJson(List<FlightInfoEntity> flights, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setHeader("Content-Disposition", "attachment; filename=FilteredFlightInfo.json");
		ObjectMapper objectMapper = null;

		try {
			objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(flights));
		} catch (IOException e) {
			throw new RuntimeException("Failed to write JSON", e);
		}
	}

	/**
	 * Export flights to pdf.
	 *
	 * @param flights the flights
	 * @param response the response
	 */
	private void exportFlightsToPdf(List<FlightInfoEntity> flights, HttpServletResponse response) {

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=FilteredFlightInfo.pdf");

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			for (FlightInfoEntity flight : flights) {
				document.add(new Paragraph("Flight Number:" + flight.getFlightNo()));
				document.add(new Paragraph("Airline: " + flight.getAirline()));
				document.add(new Paragraph("Origin: " + flight.getOrigin()));
				document.add(new Paragraph("Destination: " + flight.getDestination()));
				document.add(new Paragraph("Departure Time: " + flight.getDepartureTime().toString()));
				document.add(new Paragraph("Arrival Time: " + flight.getArrivalTime().toString()));
				document.add(new Paragraph(" "));
			}

			document.close();
		} catch (DocumentException | IOException e) {
			throw new RuntimeException("Failed to write PDF", e);
		}

	}

	/**
	 * Export flights to csv.
	 *
	 * @param flights the flights
	 * @param response the response
	 */
	private void exportFlightsToCsv(List<FlightInfoEntity> flights, HttpServletResponse response) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=FilteredFlightInfo.csv");

		try (CSVWriter writer = new CSVWriter(response.getWriter())) {
			String[] header = { "Flight Number", "Airline", "Origin", "Destination", "Departure Time", "Arrival Time" };
			writer.writeNext(header);

			for (FlightInfoEntity flight : flights) {
				writer.writeNext(new String[] { flight.getFlightNo(), flight.getAirline(), flight.getOrigin(),
						flight.getDestination(), flight.getDepartureTime().toString(),
						flight.getArrivalTime().toString() });
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to write CSV", e);
		}

	}

	/**
	 * Gets the flight info.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return the flight info
	 */
	private List<FlightInfoEntity> getFlightInfo(LocalDate fromDate, LocalDate toDate) {
		try {
			List<FlightInfoEntity> flightInfoEntity = flightInfoRepository.findByDepartureTimeBetween(fromDate, toDate);
			return flightInfoEntity;
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve flight information from database: " + e.getMessage());
		}
	}

}
