package report.models;

public class ReportRequest {
    private double price;
    private double upkeep;
    private String name;

    public ReportRequest() {this(null,0, 0);}
    public ReportRequest(String name, double price, double upkeep) {
        this.name = name;
        this.price = price;
        this.upkeep = upkeep;

    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public double getUpkeep() { return upkeep; }

    public void setUpkeep(double upkeep) { this.upkeep = upkeep; }
}
