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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ReportEntityServiceTest {

    /*
    @MockBean
    ReportRepository reportRepository;
     */
    @MockBean
    RestTemplate restTemplate;

    @Autowired
    ReportServiceImpl reportService;

    @Test
    void testCreateUserReport() throws JSONException {

        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setSomeone(1L);
        reportRequest.setDays(7);
        double expectedIncome = 100.0;

        // Mocking the response of restTemplate.getForEntity()
        ResponseEntity<String> responseEntity = new ResponseEntity<>("[{\"FieldId\":1,\"date\":\"2024-01-01\",\"timeStart\":\"08:00\",\"timeEnd\":\"12:00\"}]", HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

        // Mocking the behavior of calculateUserIncome() method
        when(reportService.calculateUserIncome(1L, reportRequest.getDays())).thenReturn(expectedIncome);

        Report report = reportService.createUserReport(reportRequest);

        assertEquals(123L, report.getSomeone());
        assertEquals(7, report.getDays());
        assertEquals(100.0, report.getIncome());
        assertEquals(100.0, report.getProfit());
    }


    /*
    @Test
    public void testRepo() {

        Report reportToBeSaved = new Report("Mario", 10, 1, 0);
        Report expectedReport = new Report(1L, "Mario", 10, 1, 0, 9);

        ReportEntity outputReport = new ReportEntity(1L, "Mario", 10, 1, 0, 9);
        when(reportRepository.save(any(ReportEntity.class))).thenReturn(outputReport);

        Report savedReport = reportService.saveReport(reportToBeSaved);

        assertThat(savedReport).isEqualToComparingFieldByField(expectedReport);
        verify(reportRepository, times(1)).save(any(ReportEntity.class));
    }

     */

    /*
    @Test
    public void testFurtherUpdate() {
        ReportEntity existingReport = new ReportEntity(1L, "Mario", 10, 1, 0, 9);
        Report request = new Report("Rossi", 8, 2, 0);
        Report expectedReport = new Report(1L, "Rossi", 8, 2, 0, 6);
        ReportEntity entityReport = new ReportEntity(1L, "Rossi", 8, 2, 0, 6);


        when(reportRepository.getOne(1L)).thenReturn(existingReport);
        when(reportRepository.save(any(ReportEntity.class))).thenReturn(entityReport);
        Report updatedReport = reportService.updateReport(request, 1L);

        assertThat(updatedReport).isEqualToComparingFieldByField(expectedReport);
    }

     */

}
