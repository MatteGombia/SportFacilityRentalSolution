package booking.controller;

import booking.StartApplication;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookingControllerIntegrationTest {
    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private BookingService bookingService;

    @Test
    public void testCreateValidBooking(){
        BookingRequest bookingRequest = new BookingRequest(1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        BookingResponse bookingResponse = new BookingResponse(0L,1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        ResponseEntity<BookingResponse> responseEntity = testRestTemplate.postForEntity("/booking", bookingRequest, BookingResponse.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
