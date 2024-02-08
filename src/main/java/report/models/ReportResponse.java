package report.models;

public class ReportResponse extends ReportRequest {

    private double profit;

    public ReportResponse() { this(0); }

    public ReportResponse(double profit) {
        this.profit = profit;
    }

    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }

}
