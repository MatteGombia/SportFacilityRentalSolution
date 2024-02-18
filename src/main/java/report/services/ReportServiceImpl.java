package report.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import report.models.Report;
import report.models.ReportRequest;
import report.utils.ReportUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.time.format.DateTimeFormatter;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReportUtils reportUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Report createUserReport(ReportRequest reportRequest) throws JSONException {

        Report report = new Report();
        report.setSomeone(reportRequest.getSomeone());
        report.setDays(30);
        report.setIncome(calculateUserIncome(reportRequest.getSomeone(), 30));
        report.setProfit(report.getIncome());
        return report;
    }

    public int hourDifference (LocalTime start, LocalTime finish) {
        long hoursDifference = ChronoUnit.HOURS.between(start, finish);
        int hoursDifferenceInt = Math.toIntExact(hoursDifference);
        return hoursDifferenceInt;
    }

    @Override
    public double calculateUserIncome(Long user, int days) throws JSONException{

        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        String endpoint = "/booking/user/" + user;
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(endpoint, String.class);

        JSONArray array = new JSONArray(responseEntity.getBody());

        JSONArrayIterator iterator = new JSONArrayIterator(array);
        LocalDate thresholdDate = LocalDate.now().minusDays(days);


        if(responseEntity.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("Error in calling the API field");

        double totalIncome = 0.0;
        int idx = 0;

        while (iterator.hasNext()) {
            JSONObject booking = iterator.next();
            String endpointField = "/fields/" + booking.getLong("FieldId");
            ResponseEntity<String> responseFieldEntity =
                    restTemplate.getForEntity(endpointField, String.class);
            JSONObject field = array.getJSONObject(idx);
            LocalDate bookingDate = LocalDate.parse(booking.getString("date"));
            LocalTime bookingStartTime = LocalTime.parse(booking.getString("timeStart"));
            LocalTime bookingEndTime = LocalTime.parse(booking.getString("timeEnd"));
            if (bookingDate.isAfter(thresholdDate)) {
                double price = field.getDouble("price");
                int hours = hourDifference(bookingStartTime, bookingEndTime);
                totalIncome += (price * hours);
            }
            idx++;
        }

        return totalIncome;
    }

    @Override
    public Report createFieldReport(ReportRequest reportRequest) throws JSONException {


        Report report = new Report();
        report.setSomeone(reportRequest.getSomeone());
        report.setDays(30);
        report.setIncome(calculateFieldIncome(report.getSomeone(), 30));
        String endpoint = "/fields/" + report.getSomeone();
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(endpoint, String.class);
        JSONObject field = new JSONObject(responseEntity.getBody());

        report.setProfit(report.getIncome() - field.getDouble("maintenance") * 30);
        return report;
    }

    public double calculateFieldIncome(Long field, int days) throws JSONException{

        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        String endpoint = "/booking/field/" + field;
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(endpoint, String.class);

        JSONArray array = new JSONArray(responseEntity.getBody());
        JSONArrayIterator iterator = new JSONArrayIterator(array);
        LocalDate thresholdDate = LocalDate.now().minusDays(days);


        if(responseEntity.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException("Error in calling the API field");

        double totalIncome = 0.0;

        while (iterator.hasNext()) {
            JSONObject booking = iterator.next();
            String endpointField = "/fields/" + booking.getLong("FieldId");
            ResponseEntity<String> responseFieldEntity =
                    restTemplate.getForEntity(endpointField, String.class);
            JSONObject fields = new JSONObject(responseFieldEntity.getBody());
            LocalDate bookingDate = LocalDate.parse(booking.getString("date"));
            LocalTime bookingStartTime = LocalTime.parse(booking.getString("timeStart"));
            LocalTime bookingEndTime = LocalTime.parse(booking.getString("timeEnd"));
            if (bookingDate.isAfter(thresholdDate)) {
                double price = fields.getDouble("price");
                int hours = hourDifference(bookingStartTime, bookingEndTime);
                totalIncome += (price * hours);
            }
        }

        return totalIncome;
    }



    /*
    @Override
    public Report saveReport(Report report) {

        ReportEntity reportEntity = modelMapper.map(report, ReportEntity.class);

        reportEntity = reportRepository.save(reportEntity);

        Report savedReport = modelMapper.map(reportEntity, Report.class);

        return savedReport;
    }

     */

    /*
    @Override
    public Report getReportById(Long id) {
        try {
            ReportEntity reportEntity= reportRepository.getOne(id);
            Report report = modelMapper.map(reportEntity, Report.class);
            return report;
        } catch (EntityNotFoundException ex) {
            // Handle entity not found exception
            return null; // Or throw your own custom exception
        }
    }

     */

    /*
    @Override
    public List<Report> getAllReports() {
        List<ReportEntity> reportEntities = reportRepository.findAll();
        Type returnType = new TypeToken<List<Report>>() {}.getType();
        List<Report> reports =modelMapper.map(reportEntities, returnType);
        return reports;
    }

     */

    /*
    @Override
    public Report updateReport(Report newReport, Long id) {
        Report oldReport = getReportById(id);

        oldReport.setName(newReport.getName());
        oldReport.setPrice(newReport.getPrice());
        oldReport.setUpkeep(newReport.getUpkeep());
        oldReport.setDays(newReport.getDays());
        oldReport.setProfit(newReport.getProfit() - newReport.getUpkeep());

        Report updatedReport = saveReport(oldReport);

        return saveReport(updatedReport);
    }

     */

    /*
    @Override
    public void deleteReportById(Long id) {

        try {
            reportRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            throw new EntityNotFoundException("Couldn't resolve ID: " + id);
        }

    }

     */
}
