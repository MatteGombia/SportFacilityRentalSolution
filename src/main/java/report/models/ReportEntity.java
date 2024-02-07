package report.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReportEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    private double upkeep;
    private int days;
    private double profit;

    public ReportEntity() {this(null, null, 0, 0, 0, 0);}

    public ReportEntity(Long id, String name, double profit) {
        this.id = id;
        this.name = name;
        this.profit = profit;
    }


    public ReportEntity(Long id, String name, double price, double upkeep, int days, double profit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.upkeep = upkeep;
        this.days = days;
        this.profit = price - upkeep;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
    public double getUpkeep() { return upkeep; }
    public void setUpkeep(double upkeep) { this.upkeep = upkeep; }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getDays() {return days;}
    public void setDays(int days) {this.days = days;}
    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }

}
