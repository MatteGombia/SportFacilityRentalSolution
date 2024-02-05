package booking.controllers;

import booking.models.Booking;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;


import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookingControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private BookingService reportMockService;

    @Test
    public void testCreateBooking() throws JsonProcessingException, JSONException {
        BookingRequest bookingRequest = new BookingRequest(1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        BookingResponse expectedBookingResponse = new BookingResponse(1L,1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        String expectedReportResponseBody = om.writeValueAsString(expectedBookingResponse);

        String endpoint = "/booking";
        Booking bookingReport = new Booking(1L,1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        when(reportMockService.saveBooking(any(Booking.class))).thenReturn(bookingReport);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, bookingRequest, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedReportResponseBody, responseEntity.getBody(), true);
    }


    /*

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
