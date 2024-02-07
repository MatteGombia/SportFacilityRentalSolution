package booking.controllers;

import booking.models.Booking;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.services.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookingControllerRestTemplateTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private BookingService bookingMockService;

    @Test
    public void testCreateBooking() throws JSONException {
        BookingRequest bookingRequest = new BookingRequest(1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));
        BookingResponse expectedBookingResponse = new BookingResponse(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        String endpoint = "/booking";
        Booking serviceBooking = new Booking(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        when(bookingMockService.saveBooking(any(Booking.class))).thenReturn(serviceBooking);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, bookingRequest, String.class);

        JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
        CheckEveryField(jsonResponse, expectedBookingResponse);

        verify(bookingMockService, times(1)).saveBooking(any(Booking.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchBookingById() throws JSONException {
        BookingResponse expectedBookingResponse = new BookingResponse(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        String endpoint = "/booking/1";
        Booking serviceBooking = new Booking(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        when(bookingMockService.getBookingById(any(Long.class))).thenReturn(serviceBooking);

        ResponseEntity<String> responseEntity =
                testRestTemplate.getForEntity(endpoint, String.class);
        System.out.println(responseEntity.getBody());

        JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
        CheckEveryField(jsonResponse, expectedBookingResponse);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(bookingMockService, times(1)).getBookingById(any(Long.class));
    }

    @Test
    public void testSearchBookingByUser() throws JSONException {
        BookingResponse expectedBookingResponse = new BookingResponse(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        String endpoint = "/booking/user/1";
        Booking serviceBooking = new Booking(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));
        List<Booking> serviceBookingList = new ArrayList<>();
        serviceBookingList.add(serviceBooking);

        when(bookingMockService.getBookingByUser(any(Long.class))).thenReturn(serviceBookingList);

        ResponseEntity<String> responseEntity =
                testRestTemplate.getForEntity(endpoint, String.class);
        JSONArray jsonResponseArray = new JSONArray(responseEntity.getBody());

        Assertions.assertEquals(1, jsonResponseArray.length());

        JSONObject jsonResponse = jsonResponseArray.getJSONObject(0);
        CheckEveryField(jsonResponse, expectedBookingResponse);

        verify(bookingMockService, times(1)).getBookingByUser(any(Long.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchBookingByField() throws JSONException {
        BookingResponse expectedBookingResponse = new BookingResponse(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        String endpoint = "/booking/field/1";
        Booking serviceBooking = new Booking(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));
        List<Booking> serviceBookingList = new ArrayList<>();
        serviceBookingList.add(serviceBooking);

        when(bookingMockService.getBookingByField(any(Long.class))).thenReturn(serviceBookingList);

        ResponseEntity<String> responseEntity =
                testRestTemplate.getForEntity(endpoint, String.class);
        JSONArray jsonResponseArray = new JSONArray(responseEntity.getBody());
        JSONObject jsonResponse = jsonResponseArray.getJSONObject(0);

        Assertions.assertEquals(1, jsonResponseArray.length());

        CheckEveryField(jsonResponse, expectedBookingResponse);

        verify(bookingMockService, times(1)).getBookingByField(any(Long.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBooking() throws JSONException {
        String endpoint = "/booking/1";
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        doNothing().when(bookingMockService).deleteBookingById(any(Long.class));

        // Act
        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.DELETE, entity, String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(bookingMockService, times(1)).deleteBookingById(any(Long.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchAllBooking() throws JSONException {
        BookingResponse expectedBookingResponse = new BookingResponse(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));
        BookingResponse expectedBookingResponse2 = new BookingResponse(2L,2L,2L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));


        String endpoint = "/booking";
        Booking serviceBooking = new Booking(1L,1L,1L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));
        Booking serviceBooking2 = new Booking(2L,2L,2L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        List<Booking> serviceBookingList = new ArrayList<>();
        serviceBookingList.add(serviceBooking);
        serviceBookingList.add(serviceBooking2);

        when(bookingMockService.getAllBooking()).thenReturn(serviceBookingList);

        ResponseEntity<String> responseEntity =
                testRestTemplate.getForEntity(endpoint, String.class);
        JSONArray jsonResponseArray = new JSONArray(responseEntity.getBody());

        JSONObject jsonResponse = jsonResponseArray.getJSONObject(0);
        CheckEveryField(jsonResponse, expectedBookingResponse);

        jsonResponse = jsonResponseArray.getJSONObject(1);
        CheckEveryField(jsonResponse, expectedBookingResponse2);

        Assertions.assertEquals(2, jsonResponseArray.length());

        verify(bookingMockService, times(1)).getAllBooking();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    void CheckEveryField(JSONObject jsonResponse, BookingResponse expectedBookingResponse) throws JSONException {
        Assertions.assertEquals(jsonResponse.getLong("user"), expectedBookingResponse.getUser());
        Assertions.assertEquals(jsonResponse.getLong("field"), expectedBookingResponse.getField());
        Assertions.assertEquals(jsonResponse.getInt("id"), expectedBookingResponse.getId());
        Assertions.assertEquals(jsonResponse.getString("date"), expectedBookingResponse.getDate().toString());
        Assertions.assertEquals(jsonResponse.getString("timeStart"), expectedBookingResponse.getTimeStart().format(DateTimeFormatter.ISO_TIME).toString());
        Assertions.assertEquals(jsonResponse.getString("timeEnd"), expectedBookingResponse.getTimeEnd().format(DateTimeFormatter.ISO_TIME).toString());
    }
}
