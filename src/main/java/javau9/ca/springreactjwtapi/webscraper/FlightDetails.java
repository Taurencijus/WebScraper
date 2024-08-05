package javau9.ca.springreactjwtapi.webscraper;

public class FlightDetails {
    private String number;
    private Airport airportDeparture;
    private Airport airportArrival;
    private String dateDeparture;
    private String dateArrival;
    private String companyCode;
    private double importTaxAdl;

    public FlightDetails(String number, Airport airportDeparture, Airport airportArrival, String dateDeparture, String dateArrival, String companyCode, double importTaxAdl) {
        this.number = number;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;
        this.dateDeparture = dateDeparture;
        this.dateArrival = dateArrival;
        this.companyCode = companyCode;
        this.importTaxAdl = importTaxAdl;
    }

    public String getNumber() { return number; }
    public Airport getAirportDeparture() { return airportDeparture; }
    public Airport getAirportArrival() { return airportArrival; }
    public String getDateDeparture() { return dateDeparture; }
    public String getDateArrival() { return dateArrival; }
    public String getCompanyCode() { return companyCode; }
    public double getImportTaxAdl() { return importTaxAdl; }
}