package report.services;

import org.modelmapper.TypeToken;
import org.springframework.web.client.RestTemplate;
import report.models.Report;
import report.models.ReportEntity;
import report.models.ReportRequest;
import report.repositories.ReportRepository;
import report.utils.ReportUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReportUtils reportUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Report createUserReport(ReportRequest reportRequest) {

        Report report = new Report();
        report.setSomeone(reportRequest.getSomeone());
        report.setDays(reportRequest.getDays());
        report.setIncome(calculateUserIncome(report.getSomeone(), report.getDays());
        report.setMaintenance(0);
        report.setProfit(report.calculateProfit(report.getIncome(), report.getMaintenance(), report.getDays()));
        return report;
    }

    public int hourDifference (LocalTime start, LocalTime finish) {
        long hoursDifference = ChronoUnit.HOURS.between(start, finish);
        int hoursDifferenceInt = Math.toIntExact(hoursDifference);
        return hoursDifferenceInt;
    }

    @Override
    public double calculateUserIncome(Long user, int days) {
        // Step 1: Retrieve User's Bookings within the specified number of days
        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        String bookingServiceUrl = "http://booking-service-hostname/booking/user/" + user + "?startDate=" + startDate;
        ResponseEntity<List<BookingResponse>> responseEntity = restTemplate.getForEntity(bookingServiceUrl, List.class);
        List<BookingResponse> bookingResponses = responseEntity.getBody();

        double totalIncome = 0.0;

        // Step 2 & 3: Retrieve Field Prices and Calculate Income for each booking
        for (BookingResponse booking : bookingResponses) {
            // Calculate duration of the booking in hours
            Duration duration = Duration.between(booking.getTimeSTart(), booking.getTimeFinish());
            int hours = (int) duration.toHours(); // Convert to int

            // Retrieve field price from field microservice
            double fieldPrice = getFieldPrice(booking.getField());

            // Calculate income for this booking
            double bookingIncome = hours * fieldPrice;

            // Add booking income to total income
            totalIncome += bookingIncome;
        }

        return totalIncome;
    }

    @Override
    public Report createFieldReport(ReportRequest reportRequest) {
        Report report = new Report();
        report.setSomeone(reportRequest.getSomeone());
        report.setDays(reportRequest.getDays());
        report.setIncome(calculateFieldIncome(report.getSomeone(), report.getDays()));
        report.setMaintenance(reportRequest.getMaintenance());
        report.setProfit(report.calculateProfit(report.getIncome(), report.getMaintenance(), report.getDays()));
        return report;
    }

    public double calculateFieldIncome(Long field, int days) {
        // Step 1: Retrieve User's Bookings within the specified number of days
        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        String bookingServiceUrl = "http://booking-service-hostname/booking/field/" + field + "?startDate=" + startDate;
        ResponseEntity<List<BookingResponse>> responseEntity = restTemplate.getForEntity(bookingServiceUrl, List.class);
        List<BookingResponse> bookingResponses = responseEntity.getBody();

        double totalIncome = 0.0;

        // Step 2 & 3: Retrieve Field Prices and Calculate Income for each booking
        for (BookingResponse booking : bookingResponses) {
            // Calculate duration of the booking in hours
            Duration duration = Duration.between(booking.getTimeSTart(), booking.getTimeFinish());
            int hours = (int) duration.toHours(); // Convert to int

            // Retrieve field price from field microservice
            double fieldPrice = getFieldPrice(booking.getField());

            // Calculate income for this booking
            double bookingIncome = hours * fieldPrice;

            // Add booking income to total income
            totalIncome += bookingIncome;
        }

        return totalIncome;
    }

    public double getFieldPrice(Long field) {
        String url = "http://field-service-hostname/field/" + field + "/price";
        ResponseEntity<Double> responseEntity = restTemplate.getForEntity(url, Double.class);
        return responseEntity.getBody();
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
