package report.services;

import report.models.Report;
import report.models.ReportRequest;

import java.util.List;

public interface ReportService {

    //Interact with the repository (database) to perform
    //CRUD operations on fields.

    //Convert between different data representations, if necessary.

    //Handle exceptions and errors that may occur during business logic execution or data access.
    //Convert exceptions into meaningful error messages for clients.

    Report createUserReport(ReportRequest reportRequest);

    double calculateUserIncome(Long someone, int days);
    Report createFieldReport(ReportRequest reportRequest);

    double calculateFieldIncome(Long someone, int days);

    //Report saveReport(Report report);

    //List<Report> getAllReports();

    //Report getReportById(Long id);

    //Report updateReport(Report report, Long id);

    //void deleteReportById(Long id);
}
