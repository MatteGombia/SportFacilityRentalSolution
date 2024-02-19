package report.services;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import report.controllers.ReportController;
import report.models.Report;
//import report.models.ReportEntity;
import report.models.ReportRequest;
import report.models.ReportResponse;
import report.utils.ReportUtils;
//import report.repositories.ReportRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ReportEntityServiceTest {


    @MockBean
    RestTemplate restTemplate;

    @Autowired
    ReportServiceImpl reportService;

    @Test
    void testCalculateUserIncome() throws JSONException {

        String bookingJsonResponse = "[{\"FieldId\":1,\"date\":\"2024-02-15\",\"timeStart\":\"10:00\",\"timeEnd\":\"12:00\"}]";
        ResponseEntity<String> bookingResponseEntity = new ResponseEntity<>(bookingJsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity("/booking/user/1", String.class)).thenReturn(bookingResponseEntity);

        String fieldJsonResponse = "{\"price\":50.0}";
        ResponseEntity<String> fieldResponseEntity = new ResponseEntity<>(fieldJsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity("/fields/1", String.class)).thenReturn(fieldResponseEntity);

        double income = reportService.calculateUserIncome(1L);

        assertEquals(100.0, income);

    }

    @Test
    public void testCalculateFieldIncome() throws Exception {

        String bookingJsonResponse = "[{\"FieldId\":1,\"date\":\"2024-02-15\",\"timeStart\":\"10:00\",\"timeEnd\":\"12:00\"}]";
        ResponseEntity<String> bookingResponseEntity = new ResponseEntity<>(bookingJsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity("/booking/field/1", String.class)).thenReturn(bookingResponseEntity);

        String fieldJsonResponse = "{\"price\":50.0}";
        ResponseEntity<String> fieldResponseEntity = new ResponseEntity<>(fieldJsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity("/fields/1", String.class)).thenReturn(fieldResponseEntity);

        double income = reportService.calculateFieldIncome(1L);

        assertEquals(100.0, income);
    }

    /*
    @Test
    public void testCreateFieldReport() throws Exception {

        String fieldJsonResponse = "{\"maintenance\":10.0}";
        ResponseEntity<String> fieldResponseEntity = new ResponseEntity<>(fieldJsonResponse, HttpStatus.OK);
        when(restTemplate.getForEntity("/fields/1", String.class)).thenReturn(fieldResponseEntity);

        when(reportService.calculateFieldIncome(1L)).thenReturn(300.0);

        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setSomeone(1L);

        Report report = reportService.createFieldReport(reportRequest);

        assertEquals(reportRequest.getSomeone(), report.getSomeone());
        assertEquals(300.0, report.getIncome());
        assertEquals(0.0, report.getProfit());
    }
     */


}
