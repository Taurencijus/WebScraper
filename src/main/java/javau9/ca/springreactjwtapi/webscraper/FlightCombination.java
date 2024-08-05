package javau9.ca.springreactjwtapi.webscraper;

import java.util.List;

public class FlightCombination {
    private int recommendationId;
    private double totalPrice;
    private double totalTaxes;
    private List<FlightDetails> outboundFlights;
    private List<FlightDetails> inboundFlights;

    public FlightCombination(int recommendationId, double totalPrice, double totalTaxes, List<FlightDetails> outboundFlights, List<FlightDetails> inboundFlights) {
        this.recommendationId = recommendationId;
        this.totalPrice = totalPrice;
        this.totalTaxes = totalTaxes;
        this.outboundFlights = outboundFlights;
        this.inboundFlights = inboundFlights;
    }

    public int getRecommendationId() { return recommendationId; }
    public double getTotalPrice() { return totalPrice; }
    public double getTotalTaxes() { return totalTaxes; }
    public List<FlightDetails> getOutboundFlights() { return outboundFlights; }
    public List<FlightDetails> getInboundFlights() { return inboundFlights; }
}
