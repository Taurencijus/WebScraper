package javau9.ca.springreactjwtapi.webscraper;

public class Airport {
    private String code;
    private String description;

    public Airport(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
