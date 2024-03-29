package booking.services;

import booking.models.Booking;
import booking.models.BookingEntity;
import booking.models.BookingRequest;
import booking.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class BookingServiceTest {

    @MockBean
    BookingRepository bookingRepository;

    @MockBean
    TestRestTemplate testRestTemplate;

    @Autowired
    BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testSaveBooking() throws JSONException {
        Booking bookingToBeSaved = new Booking(1L,1L,1, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        Booking expectedBooking= new Booking(1L, 1L,1L,1, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,1, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(outputBooking);
        String endpoint = "/fields/1";
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"maxCapacity\":5}",HttpStatus.OK);
        when(testRestTemplate.getForEntity(endpoint,String.class)).thenReturn(responseEntity);

        Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);

        assertThat(savedBooking).isEqualToComparingFieldByField(expectedBooking);
        verify(bookingRepository, times(1)).save(any(BookingEntity.class));
    }

    @Test
    public void testSaveBookingOverMaximumCapacity() throws JSONException {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(outputBooking);
        String endpoint = "/fields/1";
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"maxCapacity\":4}",HttpStatus.OK);
        when(testRestTemplate.getForEntity(endpoint,String.class)).thenReturn(responseEntity);

        try {
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
            assertFalse(true);
        }catch (Exception ex) {
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
        }
    }
    @Test
    public void testSaveBookingNumPeopleZero() throws JSONException {
        Booking bookingToBeSaved = new Booking(1L,1L,0, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        String endpoint = "/fields/1";
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"maxCapacity\":4}",HttpStatus.OK);
        when(testRestTemplate.getForEntity(endpoint,String.class)).thenReturn(responseEntity);

        try {
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
            assertFalse(true);
        }catch (Exception ex) {
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
            verify(testRestTemplate, times(0)).getForEntity(endpoint,String.class);
        }
    }

    @Test
    public void testSaveBookingTimeSwapped() {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(15, 30, 0), LocalTime.of(14, 30, 0));

        try{
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
        }catch (Exception ex){
            assertTrue(true);
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
        }
    }
    @Test
    public void testSaveBookingTimeStartBeforeOpening() {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(7, 59, 59), LocalTime.of(14, 30, 0));

        try{
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
            assertTrue(false);
        }catch (Exception ex){
            assertTrue(true);
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
        }
    }
    @Test
    public void testSaveBookingTimeEndAfterClosing() {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(15, 59, 59), LocalTime.of(20, 00, 01));

        try{
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
            assertTrue(false);
        }catch (Exception ex){
            assertTrue(true);
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
        }
    }

    @Test
    public void testSaveBookingTimeClashStart() {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(15, 30, 0), LocalTime.of(16, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(15, 00, 0), LocalTime.of(16, 00, 0));
        List<BookingEntity> returnValues = new ArrayList<>();
        returnValues.add(outputBooking);

        when(bookingRepository.findAll()).thenReturn(returnValues);

        try{
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            assertTrue(true);
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
            verify(bookingRepository, times(1)).findAll();
        }
    }
    @Test
    public void testSaveBookingTimeClashEnd() {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(15, 30, 0), LocalTime.of(16, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(16, 00, 0), LocalTime.of(17, 00, 0));
        List<BookingEntity> returnValues = new ArrayList<>();
        returnValues.add(outputBooking);

        when(bookingRepository.findAll()).thenReturn(returnValues);

        try{
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            assertTrue(true);
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
            verify(bookingRepository, times(1)).findAll();
        }
    }

    @Test
    public void testSaveBookingTimeClashContained() {
        Booking bookingToBeSaved = new Booking(1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(15, 30, 0), LocalTime.of(16, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(15, 45, 0), LocalTime.of(16, 15, 0));
        List<BookingEntity> returnValues = new ArrayList<>();
        returnValues.add(outputBooking);

        when(bookingRepository.findAll()).thenReturn(returnValues);

        try{
            Booking savedBooking = bookingService.saveBooking(bookingToBeSaved);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            assertTrue(true);
            verify(bookingRepository, times(0)).save(any(BookingEntity.class));
            verify(bookingRepository, times(1)).findAll();
        }
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

    @Test
    public void testUpdateBooking() throws JSONException {
        BookingRequest bookingRequest = new BookingRequest(1L,2L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        Booking expectedBooking = new Booking(1L, 1L,2L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        BookingEntity outputBooking = new BookingEntity(1L, 1L,2L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));


        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(outputBooking);
        when(bookingRepository.getOne(any(Long.class))).thenReturn(outputBooking);
        String endpoint = "/fields/2";
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"maxCapacity\":5}",HttpStatus.OK);
        System.out.println(responseEntity.getBody());
        when(testRestTemplate.getForEntity(endpoint,String.class)).thenReturn(responseEntity);

        Booking actualBooking = bookingService.updateBooking(1L, bookingRequest);

        assertThat(actualBooking).isEqualToComparingFieldByField(expectedBooking);

        verify(bookingRepository, times(1)).save(any(BookingEntity.class));
        verify(bookingRepository, times(1)).getOne(any(Long.class));
        verify(testRestTemplate, times(1)).getForEntity(endpoint,String.class);
    }

    @Test
    public void testFindAllBooking() {
        Booking expectedBooking = new Booking(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        Booking expectedBooking2 = new Booking(2L, 2L,2L,6, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));


        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));
        BookingEntity outputBooking2 = new BookingEntity(2L, 2L,2L,6, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        List<BookingEntity> returnValues = new ArrayList<>();
        returnValues.add(outputBooking);
        returnValues.add(outputBooking2);

        when(bookingRepository.findAll()).thenReturn(returnValues);

        List<Booking> foundBookings = bookingService.getAllBooking();
        assertEquals(2, foundBookings.size());
        assertThat(foundBookings.get(0)).isEqualToComparingFieldByField(expectedBooking);
        assertThat(foundBookings.get(1)).isEqualToComparingFieldByField(expectedBooking2);
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    public void TestDeleteById() {
        BookingEntity outputBooking = new BookingEntity(1L, 1L,1L,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0));

        doNothing().when(bookingRepository).deleteById(any(Long.class));

        bookingService.deleteBookingById(1L);

        verify(bookingRepository, times(1)).deleteById(any(Long.class));
    }

}
