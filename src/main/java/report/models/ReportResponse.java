package report.models;

public class ReportResponse extends ReportRequest {

    private Long id;
    private double profit;

    public ReportResponse() { this(null,null, 0, 0, 0, 0); }

    public ReportResponse(Long id, String name, double price, double upkeep, int days, double profit) {
        super(name, price, upkeep, days);
        this.profit = price - upkeep;
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
