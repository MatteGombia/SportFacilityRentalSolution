package report.models;

public class Report {
    private Long someone;
    private double income;
    private double maintenance;
    private int days;
    private double profit;

    public Report() { this( null, 0, 0, 0, 0); }


    public Report(Long someone, int days, double income, double maintenance, double profit) {
        this.someone = someone;
        this.days = days;
        this.income = income;
        this.maintenance = maintenance;
        this.profit = profit;
    }


    public double calculateProfit(double income, double maintenance, int days) {
        double result = income - maintenance * days;
        return result;
    }


    /*
    public Long getUser() { return user; }
    public void setUser(Long user) { this.user = user; }
    public Long getField() { return field;}
    public void setField(Long field) { this.field = field; }

     */
    public Long getSomeone() { return someone; }
    public void setSomeone(Long someone) { this.someone = someone;}
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
    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
