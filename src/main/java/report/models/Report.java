package report.models;

public class Report {
    private Long id;
    private String name;
    private double price;
    private double upkeep;
    private double profit;

    public Report() { this(null, 0, 0); }

    public Report(String name, double price, double upkeep) {
        this(null, name, price, upkeep, 0);
    }

    public Report(Long id, String name, double price, double upkeep, double profit) {
        this.setId(id);
        this.name = name;
        this.price = price;
        this.upkeep = upkeep;
        this.setProfit(price - upkeep);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getUpkeep() { return upkeep; }
    public void setUpkeep(double upkeep) { this.upkeep = upkeep; }
    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
