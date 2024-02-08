package report.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ReportControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ReportService reportMockService;


    @Test
    public void testCreateReport() throws JsonProcessingException, JSONException {
        ReportRequest reportRequest = new ReportRequest("Mario",10, 1, 0);
        ReportResponse expectedReportResponse = new ReportResponse(1L, "Mario", 10, 1, 0, 9);

        String expectedReportResponseBody = om.writeValueAsString(expectedReportResponse);

        String endpoint = "/reports";
        Report serviceReport = new Report(1L, "Mario", 10, 1, 0, 9);

        when(reportMockService.saveReport(any(Report.class))).thenReturn(serviceReport);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, reportRequest, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedReportResponseBody, responseEntity.getBody(), true);
    }

    /*
    @Test
    public void testCreateValidField() throws Exception {
        FieldRequest fieldRequest = new FieldRequest("Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        FieldResponse expectedFieldResponse =new FieldResponse(1L, "Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        String expectedResponseBody = om.writeValueAsString(expectedFieldResponse);

        String endpoint = "/fields";

        Field serviceField = new Field(1L, "Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldMockService.saveField(any(Field.class))).thenReturn(serviceField);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, fieldRequest, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testDeleteValidField() {
        Long fieldId = 1L;

        String endpoint = "/fields/{id}";

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                endpoint,
                HttpMethod.DELETE,
                null,
                String.class,
                fieldId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());



        assertNull(responseEntity.getBody());
    }
    */

    /*@Test
    public void testGetOneField() {
        Long fieldId = 1L;

        String endpoint = "/fields/{id}";

        ResponseEntity<FieldResponse> responseEntity = testRestTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                FieldResponse.class,
                fieldId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

       FieldResponse expectedFieldResponse = new FieldResponse(1L, "Football field", 20.50, 45,
               "Mosta, Brown street 23", "Keys to the field are at watchman post");

        assertThat(responseEntity.getBody()).isEqualTo(expectedFieldResponse);
    }*/
}