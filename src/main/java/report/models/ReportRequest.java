package report.models;

public class ReportRequest {
    private Long someone;
    //private int days;
    public ReportRequest() {this(null);}

    public ReportRequest(Long someone) {
        this.someone = someone;
    }


    public Long getSomeone() { return someone;}
    public void setSomeone(Long someone) { this.someone = someone; }
    /*
    public int getDays() {return days;}
    public void setDays(int days) {this.days = 30;}
    */


}
