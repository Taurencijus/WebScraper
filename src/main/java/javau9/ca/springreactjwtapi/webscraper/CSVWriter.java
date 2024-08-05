package javau9.ca.springreactjwtapi.webscraper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVWriter {
    public static void writeFlightsToCsv(List<FlightCombination> flightCombinations, String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                     "RecommendationId", "TotalPrice", "TotalTaxes",
                     "Outbound 1 Airport Departure", "Outbound 1 Airport Arrival", "Outbound 1 Time Departure", "Outbound 1 Time Arrival", "Outbound 1 Flight Number",
                     "Outbound 2 Airport Departure", "Outbound 2 Airport Arrival", "Outbound 2 Time Departure", "Outbound 2 Time Arrival", "Outbound 2 Flight Number",
                     "Inbound 1 Airport Departure", "Inbound 1 Airport Arrival", "Inbound 1 Time Departure", "Inbound 1 Time Arrival", "Inbound 1 Flight Number",
                     "Inbound 2 Airport Departure", "Inbound 2 Airport Arrival", "Inbound 2 Time Departure", "Inbound 2 Time Arrival", "Inbound 2 Flight Number"
             ))) {

            for (FlightCombination combination : flightCombinations) {
                csvPrinter.printRecord(
                        combination.getRecommendationId(),
                        combination.getTotalPrice(),
                        combination.getTotalTaxes(),
                        getFlightDetail(combination.getOutboundFlights(), 0, "departure"),
                        getFlightDetail(combination.getOutboundFlights(), 0, "arrival"),
                        getFlightDetail(combination.getOutboundFlights(), 0, "departureTime"),
                        getFlightDetail(combination.getOutboundFlights(), 0, "arrivalTime"),
                        getFlightDetail(combination.getOutboundFlights(), 0, "flightNumber"),
                        getFlightDetail(combination.getOutboundFlights(), 1, "departure"),
                        getFlightDetail(combination.getOutboundFlights(), 1, "arrival"),
                        getFlightDetail(combination.getOutboundFlights(), 1, "departureTime"),
                        getFlightDetail(combination.getOutboundFlights(), 1, "arrivalTime"),
                        getFlightDetail(combination.getOutboundFlights(), 1, "flightNumber"),
                        getFlightDetail(combination.getInboundFlights(), 0, "departure"),
                        getFlightDetail(combination.getInboundFlights(), 0, "arrival"),
                        getFlightDetail(combination.getInboundFlights(), 0, "departureTime"),
                        getFlightDetail(combination.getInboundFlights(), 0, "arrivalTime"),
                        getFlightDetail(combination.getInboundFlights(), 0, "flightNumber"),
                        getFlightDetail(combination.getInboundFlights(), 1, "departure"),
                        getFlightDetail(combination.getInboundFlights(), 1, "arrival"),
                        getFlightDetail(combination.getInboundFlights(), 1, "departureTime"),
                        getFlightDetail(combination.getInboundFlights(), 1, "arrivalTime"),
                        getFlightDetail(combination.getInboundFlights(), 1, "flightNumber")
                );
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFlightDetail(List<FlightDetails> flightDetails, int index, String detail) {
        if (index >= flightDetails.size()) {
            return "";
        }

        FlightDetails details = flightDetails.get(index);

        switch (detail) {
            case "departure":
                return details.getAirportDeparture().getCode();
            case "arrival":
                return details.getAirportArrival().getCode();
            case "departureTime":
                return details.getDateDeparture();
            case "arrivalTime":
                return details.getDateArrival();
            case "flightNumber":
                return details.getCompanyCode() + details.getNumber();
            default:
                return "";
        }
    }
}
