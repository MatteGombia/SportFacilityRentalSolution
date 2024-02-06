package booking.controllers;

import booking.models.Booking;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.convention.MatchingStrategies;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;


import java.awt.print.Book;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

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
        BookingRequest bookingRequest = new BookingRequest(1,1,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));
        BookingResponse expectedBookingResponse = new BookingResponse(1L,1,1,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        String endpoint = "/booking";
        Booking serviceBooking = new Booking(1L,1,1,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        when(reportMockService.saveBooking(any(Booking.class))).thenReturn(serviceBooking);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, bookingRequest, String.class);
        JSONObject jsonResponse = new JSONObject(responseEntity.getBody());

        Assertions.assertEquals(jsonResponse.getInt("user"), expectedBookingResponse.getUser());
        Assertions.assertEquals(jsonResponse.getInt("field"), expectedBookingResponse.getField());
        Assertions.assertEquals(jsonResponse.getInt("id"), expectedBookingResponse.getId());
        Assertions.assertEquals(jsonResponse.getString("date"), expectedBookingResponse.getDate().toString());
        Assertions.assertEquals(jsonResponse.getString("timeStart"), expectedBookingResponse.getTimeStart().format(DateTimeFormatter.ISO_TIME).toString());
        Assertions.assertEquals(jsonResponse.getString("timeEnd"), expectedBookingResponse.getTimeEnd().format(DateTimeFormatter.ISO_TIME).toString());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
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
