package javau9.ca.springreactjwtapi.webscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class WebscraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebscraperApplication.class, args);

        String urlTemplate = "http://homeworktask.infare.lt/search.php?from=%s&to=%s&depart=%s&return=%s";
        String[][] routes = {
                {"MAD", "FUE", "2024-08-09", "2024-08-16"}
        };

        FlightDataFetcher dataFetcher = new FlightDataFetcher();

        for (String[] route : routes) {
            String url = String.format(urlTemplate, route[0], route[1], route[2], route[3]);
            List<FlightCombination> flightCombinations = dataFetcher.fetchFlightData(url);

            Optional<Double> minTotalPriceOpt = flightCombinations.stream()
                    .map(fc -> fc.getTotalPrice() + fc.getTotalTaxes())
                    .min(Double::compareTo);

            if (minTotalPriceOpt.isPresent()) {
                double minTotalPrice = minTotalPriceOpt.get();

                List<FlightCombination> cheapestFlights = flightCombinations.stream()
                        .filter(fc -> (fc.getTotalPrice() + fc.getTotalTaxes()) == minTotalPrice)
                        .collect(Collectors.toList());

                String fileName = String.format("%s-%s_(%s)-(%s).csv", route[0], route[1], route[2], route[3]);
                CSVWriter.writeFlightsToCsv(cheapestFlights, fileName);
            }
        }
    }
}
