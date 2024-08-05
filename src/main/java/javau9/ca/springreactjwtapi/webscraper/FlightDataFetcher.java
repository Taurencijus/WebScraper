package javau9.ca.springreactjwtapi.webscraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class FlightDataFetcher {

    public List<FlightCombination> fetchFlightData(String url) {
        List<FlightCombination> flightCombinations = null;

        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String json = doc.body().text();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            flightCombinations = FlightDataExtractor.extractFlightCombinations(rootNode);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return flightCombinations;
    }
}
