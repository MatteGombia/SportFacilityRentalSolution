package report.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import report.models.Report;
import report.models.ReportEntity;
import report.models.ReportRequest;
import report.models.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import report.services.ReportService;
import report.controllers.ReportController;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerFurtherTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportMockService;

    @Test
    public void testUpdate() throws Exception {

        ReportRequest updateReportRequest = new ReportRequest("Rossi", 8, 2);

        Long reportId = 1L;

        Report outputReport = new Report(reportId, "Rossi", 8, 2, 6);

        when(reportMockService.getReportById(reportId)).thenReturn(outputReport);
        when(reportMockService.saveReport(any(Report.class))).thenReturn(outputReport);

        String request = om.writeValueAsString(updateReportRequest);

        String endpoint = "/reports/" + reportId;

        // Act
        ResultActions resultActions = mockMvc.perform(
                put(endpoint)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rossi"))
                .andExpect(jsonPath("$.price").value(8))
                .andExpect(jsonPath("$.upkeep").value(2))
                .andExpect(jsonPath("$.id").value(reportId))
                .andExpect(jsonPath("$.profit").value(6));

        verify(reportMockService, times(1)).getReportById(reportId);
        verify(reportMockService, times(1)).saveReport(any(Report.class));



        //verify(reportMockService).saveReport(getReport);
    }
}
