package javau9.ca.springreactjwtapi.webscraper;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class FlightDataExtractor {

    public static List<FlightCombination> extractFlightCombinations(JsonNode rootNode) {
        List<FlightCombination> flightCombinations = new ArrayList<>();
        JsonNode totalAvailabilities = rootNode.path("body").path("data").path("totalAvailabilities");
        JsonNode journeys = rootNode.path("body").path("data").path("journeys");

        for (JsonNode availability : totalAvailabilities) {
            int recommendationId = availability.get("recommendationId").asInt();
            double totalPrice = availability.get("total").asDouble();

            List<FlightDetails> outboundFlights1 = new ArrayList<>();
            List<FlightDetails> outboundFlights2 = new ArrayList<>();
            List<FlightDetails> inboundFlights1 = new ArrayList<>();
            List<FlightDetails> inboundFlights2 = new ArrayList<>();

            double totalOutboundTaxes = 0.0;
            double totalInboundTaxes = 0.0;

            for (JsonNode journey : journeys) {
                if (journey.path("recommendationId").asInt() == recommendationId) {
                    List<FlightDetails> flights = new ArrayList<>();
                    for (JsonNode flight : journey.path("flights")) {
                        flights.add(new FlightDetails(
                                flight.path("number").asText(),
                                new Airport(flight.path("airportDeparture").path("code").asText(), flight.path("airportDeparture").path("description").asText()),
                                new Airport(flight.path("airportArrival").path("code").asText(), flight.path("airportArrival").path("description").asText()),
                                flight.path("dateDeparture").asText(),
                                flight.path("dateArrival").asText(),
                                flight.path("companyCode").asText(),
                                journey.path("importTaxAdl").asDouble()
                        ));
                    }

                    if ("I".equals(journey.path("direction").asText())) {
                        if (outboundFlights1.isEmpty()) {
                            outboundFlights1.addAll(flights);
                            totalOutboundTaxes = journey.path("importTaxAdl").asDouble();
                        } else {
                            outboundFlights2.addAll(flights);
                            totalOutboundTaxes = journey.path("importTaxAdl").asDouble();
                        }
                    } else if ("V".equals(journey.path("direction").asText())) {
                        if (inboundFlights1.isEmpty()) {
                            inboundFlights1.addAll(flights);
                            totalInboundTaxes = journey.path("importTaxAdl").asDouble();
                        } else {
                            inboundFlights2.addAll(flights);
                            totalInboundTaxes = journey.path("importTaxAdl").asDouble();
                        }
                    }
                }
            }

            double totalTaxes = totalOutboundTaxes + totalInboundTaxes;

            flightCombinations.add(new FlightCombination(recommendationId, totalPrice, totalTaxes, outboundFlights1, inboundFlights1));
            flightCombinations.add(new FlightCombination(recommendationId, totalPrice, totalTaxes, outboundFlights1, inboundFlights2));
            flightCombinations.add(new FlightCombination(recommendationId, totalPrice, totalTaxes, outboundFlights2, inboundFlights1));
            flightCombinations.add(new FlightCombination(recommendationId, totalPrice, totalTaxes, outboundFlights2, inboundFlights2));
        }

        return flightCombinations;
    }
}
