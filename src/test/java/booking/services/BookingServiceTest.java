package booking.services;

import booking.models.Booking;
import booking.models.BookingEntity;
import booking.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
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
public class BookingServiceTest {

    @MockBean
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;


    @Test
    public void testSaveBooking() {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        Booking expectedBooking= new Booking(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(outputBooking);

        Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);

        assertThat(savedBooking).isEqualToComparingFieldByField(expectedBooking);
        verify(bookingRepository, times(1)).save(any(BookingEntity.class));
    }

    @Test
    public void testFindBookingById() {
        Booking expectedBooking = new Booking(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        when(bookingRepository.getOne(any(Long.class))).thenReturn(outputBooking);

        Booking bookingFound = bookingService.getBookingById(1L);
        assertThat(bookingFound).isEqualToComparingFieldByField(expectedBooking);
        verify(bookingRepository, times(1)).getOne(any(Long.class));
    }

    @Test
    public void testFindBookingByUser() {
        Booking expectedBooking = new Booking(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        BookingEntity bookingDiscarded = new BookingEntity(1L, 2L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        List<BookingEntity> returnValues = new ArrayList<>();
        returnValues.add(bookingDiscarded);
        returnValues.add(outputBooking);

        when(bookingRepository.findAll()).thenReturn(returnValues);

        List<Booking> foundBookings = bookingService.getBookingByUser(1L);
        assertEquals(1, foundBookings.size());
        assertThat(foundBookings.get(0)).isEqualToComparingFieldByField(expectedBooking);
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    public void testFindBookingByField() {
        Booking expectedBooking = new Booking(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        BookingEntity bookingDiscarded = new BookingEntity(1L, 1L,2L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        List<BookingEntity> returnValues = new ArrayList<>();
        returnValues.add(bookingDiscarded);
        returnValues.add(outputBooking);

        when(bookingRepository.findAll()).thenReturn(returnValues);

        List<Booking> foundBookings = bookingService.getBookingByField(1L);
        assertEquals(1, foundBookings.size());
        assertThat(foundBookings.get(0)).isEqualToComparingFieldByField(expectedBooking);
        verify(bookingRepository, times(1)).findAll();
    }

}