package report.controllers;

import org.modelmapper.TypeToken;
import org.springframework.data.crossstore.ChangeSetPersister;
import report.models.Report;
import report.models.ReportRequest;
import report.models.ReportResponse;
import report.services.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    ModelMapper modelMapper;


    /*
    List<ReportResponse> allReports(@RequestBody ReportRequest reportRequest) {
        List<Report> reports =reportService.getAllReports();
        Type responseType = new TypeToken<List<ReportResponse>>() {}.getType();

        List<ReportResponse> response = modelMapper.map(reports, responseType);
        return response;
    }
    @PostMapping("/report")
    @ResponseStatus(HttpStatus.CREATED)
    ReportResponse newReport(@RequestBody ReportRequest reportRequest) {
        Report report = modelMapper.map(reportRequest, Report.class);

        report = reportService.saveReport(report);

        ReportResponse reportResponse = modelMapper.map(report, ReportResponse.class);

        return reportResponse;
    }

     */

    @GetMapping("/report/user")
    @ResponseStatus(HttpStatus.OK)
    ReportResponse createUserReport(@RequestBody ReportRequest reportRequest) {
        Report report = reportService.createUserReport(reportRequest);
        ReportResponse response = new ReportResponse(report.getProfit());
        return response;
    }

    @GetMapping("/report/field")
    @ResponseStatus(HttpStatus.OK)
    ReportResponse createFieldReport(@RequestBody ReportRequest reportRequest) {
        Report report = reportService.createFieldReport(reportRequest);
        ReportResponse response = new ReportResponse(report.getProfit());
        return response;
    }

    /*
    @GetMapping("/report/{id}")
    @ResponseStatus(HttpStatus.OK)
    ReportResponse findOne(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        ReportResponse reportResponse = modelMapper.map(report, ReportResponse.class);
        return reportResponse;
    }

     */

    /*
    @PutMapping("/report/{id}")
    @ResponseStatus(HttpStatus.OK)
    ReportResponse updateReport(@RequestBody ReportRequest reportRequest, @PathVariable Long id) {

        Report report = modelMapper.map(reportRequest, Report.class);

        report = reportService.updateReport(report, id);

        ReportResponse response = modelMapper.map(report, ReportResponse.class);

        return response;
    }

     */

    /*
    @DeleteMapping("/report/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteReport(@PathVariable Long id) {
        reportService.deleteReportById(id);
    }

     */

}
