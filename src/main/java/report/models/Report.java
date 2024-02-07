package report.models;

public class Report {
    private Long id;
    private String name;
    private double price;
    private double upkeep;
    private int days;
    private double profit;

    public Report() { this(null, 0, 0, 0); }

    public Report(String name, double price, double upkeep, int days) {
        this(null, name, price, upkeep, days, 0);
    }

    public Report(Long id, String name, int days, double profit) {
        this.setId(id);
        this.name = name;
        this.profit = profit;
    }

    public Report(Long id, String name, double price, double upkeep, int days, double profit) {
        this.setId(id);
        this.name = name;
        this.price = price;
        this.upkeep = upkeep;
        this.days = days;
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
    public int getDays() { return days;}
    public void setDays(int days) {this.days = days;}
    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
