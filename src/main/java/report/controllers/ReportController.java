package report.controllers;

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
        return null;
    }

    ReportResponse updateReport(@RequestBody ReportRequest reportRequest, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/reports/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReport(@PathVariable Long id) {
    }

}
