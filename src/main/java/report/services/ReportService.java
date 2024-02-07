package report.services;

import report.models.Report;

import java.util.List;

public interface ReportService {

    //Interact with the repository (database) to perform
    //CRUD operations on fields.

    //Convert between different data representations, if necessary.

    //Handle exceptions and errors that may occur during business logic execution or data access.
    //Convert exceptions into meaningful error messages for clients.

    Report saveReport(Report report);

    List<Report> getAllReports();

    Report getReportById(Long id);

    Report updateReport(Report report, Long id);

    void deleteReportById(Long id);
}
