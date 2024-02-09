package report.models;

public class ReportRequest {
    private Long someone;
    private int days;
    public ReportRequest() {this(null, 0);}

    public ReportRequest(Long someone, int days) {
        this.someone = someone;
        this.days = days;
    }


    public Long getSomeone() { return someone;}
    public void setSomeone(Long someone) { this.someone = someone; }
    public int getDays() {return days;}
    public void setDays(int days) {this.days = days;}

}
