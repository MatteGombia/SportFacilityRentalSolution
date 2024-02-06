package report.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import report.models.Report;
import report.models.ReportRequest;
import report.models.ReportResponse;
import report.services.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    ModelMapper modelMapper;

    ReportResponse allReports(@RequestBody ReportRequest reportRequest) {
        return null;
    }
    @PostMapping("/reports")
    @ResponseStatus(HttpStatus.CREATED)
    ReportResponse newReport(@RequestBody ReportRequest reportRequest) {
        Report report = modelMapper.map(reportRequest, Report.class);

        report = reportService.saveReport(report);

        ReportResponse reportResponse = modelMapper.map(report, ReportResponse.class);

        return reportResponse;
    }

    @GetMapping("/reports/{id}")
    @ResponseStatus(HttpStatus.OK)
    ReportResponse findOne(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        ReportResponse reportResponse = new ReportResponse();

        reportResponse.setId(report.getId());
        reportResponse.setName(report.getName());
        reportResponse.setPrice(report.getPrice());
        reportResponse.setUpkeep(report.getProfit());
        reportResponse.setUpkeep(report.getUpkeep());

        return reportResponse;
    }

    @PutMapping("/reports/{id}")
    @ResponseStatus(HttpStatus.OK)
    ReportResponse updateReport(@RequestBody ReportRequest reportRequest, @PathVariable Long id) {

        ReportResponse getReport = findOne(id);

        Report existingReport = modelMapper.map(getReport, Report.class);

        existingReport.setName(reportRequest.getName());
        existingReport.setPrice(reportRequest.getPrice());
        existingReport.setUpkeep(reportRequest.getUpkeep());

        Report report = reportService.saveReport(existingReport);

        ReportResponse reportResponse = modelMapper.map(report, ReportResponse.class);

        /*
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setId(existingReport.getId());
        reportResponse.setName(existingReport.getName());
        reportResponse.setPrice(existingReport.getPrice());
        reportResponse.setUpkeep(existingReport.getUpkeep());
        reportResponse.setProfit(existingReport.getProfit());
        */

        return reportResponse;
    }

    @DeleteMapping("/reports/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReport(@PathVariable Long id) {
    }

}
