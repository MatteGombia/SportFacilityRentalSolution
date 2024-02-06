package booking.services;

import booking.models.Booking;
import booking.models.BookingEntity;
import booking.repositories.BookingRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class BookingEntityServiceTest {

    @MockBean
    BookingRepository bookingRepository;

    @Autowired
    BookingService reportService;

    @Autowired
    ModelMapper modelMapper;


    @Test
    public void testRepo() {
        Booking reportToBeSaved = new Booking(1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        Booking expectedReport = new Booking(1L, 1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputReport = new BookingEntity(1L, 1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(outputReport);

        Booking savedReport = reportService.saveBooking(reportToBeSaved);
        System.out.println(savedReport.getId());
        System.out.println(outputReport.getId());
        assertThat(savedReport).isEqualToComparingFieldByField(expectedReport);
        verify(bookingRepository, times(1)).save(any(BookingEntity.class));
    }
}
