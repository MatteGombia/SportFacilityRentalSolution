package report.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReportEntity {

    //private Long id;
    private Long someone;
    private double income;
    private double maintenance;
    private int days;
    private double profit;

    public ReportEntity() {this(null, 0, 0, 0, 0);}

    public ReportEntity(Long someone, int days, double income, double maintenance, double profit) {
        this.someone = someone;
        this.days = days;
        this.income = income;
        this.maintenance = maintenance;
        this.profit = profit;
    }

    public Long getSomeone() { return someone; }
    public void setSomeone(Long someone) { this.someone = someone; }
    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    public double getMaintenance() { return maintenance; }
    public void setMaintenance(double maintenance) { this.maintenance = maintenance; }
    public int getDays() { return days;}
    public void setDays(int days) {this.days = days;}
    /*
    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }

     */

}
