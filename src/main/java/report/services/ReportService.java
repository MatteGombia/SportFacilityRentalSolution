package report.services;

import org.json.JSONException;
import report.models.Report;
import report.models.ReportRequest;

import java.time.LocalTime;
import java.util.List;

public interface ReportService {

    //Interact with the repository (database) to perform
    //CRUD operations on fields.

    //Convert between different data representations, if necessary.

    //Handle exceptions and errors that may occur during business logic execution or data access.
    //Convert exceptions into meaningful error messages for clients.

    Report createUserReport(ReportRequest reportRequest) throws JSONException;

    double calculateUserIncome(Long user) throws JSONException;

    Report createFieldReport(ReportRequest reportRequest) throws JSONException;

    double calculateFieldIncome(Long someone) throws  JSONException;

    int hourDifference (LocalTime start, LocalTime finish);

    //Report saveReport(Report report);

    //List<Report> getAllReports();

    //Report getReportById(Long id);

    //Report updateReport(Report report, Long id);

    //void deleteReportById(Long id);
}
