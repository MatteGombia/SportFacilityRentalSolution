package booking.controllers;

import booking.models.Booking;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.serializers.*;
import booking.services.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookingControllerMockMvcTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingMockService;

    @Test
    public void testUpdateBooking() throws Exception {
        Long bookingId = 1L;
        BookingResponse expectedBookingResponse = new BookingResponse(bookingId,1L,2L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));
        BookingRequest updateBookingRequest = new BookingRequest(1L,2L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        // Configure the date and time format for LocalDate and LocalTime
        SimpleModule dateModule = new SimpleModule();
        dateModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        SimpleModule timeModule = new SimpleModule();
        timeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());


        om.registerModule(dateModule);
        om.registerModule(timeModule);

        String request = om.writeValueAsString(updateBookingRequest);
        //Structure of request:
        /*
        String request = "{\n" +
                "  \"user\": 1,\n" +
                "  \"field\": 2,\n" +
                "  \"numPeople\": 5,\n" +
                "  \"date\": \"2024-12-31\",\n" +
                "  \"timeStart\": \"14:30:00\",\n" +
                "  \"timeEnd\": \"15:30:00\"\n" +
                "}";
        */
        String endpoint = "/booking/1";
        Booking serviceBooking = new Booking(bookingId,1L,2L,5, LocalDate.parse("2024-12-31"), LocalTime.parse("14:30:00"), LocalTime.parse("15:30:00"));

        when(bookingMockService.updateBooking(any(Long.class), any(BookingRequest.class))).thenReturn(serviceBooking);


        // Act
        ResultActions resultActions = mockMvc.perform(
                put(endpoint)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value(1L))
                .andExpect(jsonPath("$.field").value(2L))
                .andExpect(jsonPath("$.numPeople").value(5))
                .andExpect(jsonPath("$.id").value(bookingId))
                .andExpect(jsonPath("$.date").value("2024-12-31"))
                .andExpect(jsonPath("$.timeStart").value("14:30:00"))
                .andExpect(jsonPath("$.timeEnd").value("15:30:00"));

        verify(bookingMockService, times(1)).updateBooking(any(Long.class), any(BookingRequest.class));
    }
}
