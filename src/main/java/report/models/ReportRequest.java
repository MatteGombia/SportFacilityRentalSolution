package report.models;

public class ReportRequest {
    private double income;
    private Long someone;
    private double maintenance;
    private int days;

    public ReportRequest() {this(null, 0);}

    public ReportRequest(Long someone, int days) {
        this.someone = someone;
        this.days = days;
    }

    public ReportRequest(Long someone, int days, double maintenance) {
        this.someone = someone;
        this.days = days;
        this.maintenance = maintenance;
    }


    public Long getSomeone() { return someone;}
    public void setSomeone(Long someone) { this.someone = someone; }
    public int getDays() {return days;}
    public void setDays(int days) {this.days = days;}
    public double getMaintenance() {return  maintenance;}
    public void setMaintenance(double maintenance) {this.maintenance = maintenance;}
}
