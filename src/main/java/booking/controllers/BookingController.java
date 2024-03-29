package booking.controllers;

import booking.models.Booking;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.services.BookingService;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/booking")
    @ResponseStatus(HttpStatus.OK)
    List<BookingResponse> allBooking() {
        List<BookingResponse> bookingResponse = new ArrayList<>();
        List<Booking> bookings = bookingService.getAllBooking();

        for(Booking b : bookings)
            bookingResponse.add(modelMapper.map(b, BookingResponse.class));

        return bookingResponse;
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    BookingResponse newBooking(@RequestBody BookingRequest bookingRequest) throws JSONException {

        Booking booking = modelMapper.map(bookingRequest, Booking.class);

        Booking savedBooking = bookingService.saveBooking(booking);

        BookingResponse bookingResponse = modelMapper.map(savedBooking, BookingResponse.class);

        return bookingResponse;
    }

    @GetMapping("/booking/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookingResponse SearchBookingById(@PathVariable Long id) {
        Booking foundBooking = bookingService.getBookingById(id);

        BookingResponse bookingResponse = modelMapper.map(foundBooking, BookingResponse.class);

        return bookingResponse;
    }

    @GetMapping("/booking/user/{user}")
    @ResponseStatus(HttpStatus.OK)
    List<BookingResponse> SearchBookingByUser(@PathVariable Long user) {
        List<BookingResponse> bookingResponse = new ArrayList<>();
        List<Booking> foundBookings = bookingService.getBookingByUser(user);

        for(Booking b : foundBookings)
            bookingResponse.add(modelMapper.map(b, BookingResponse.class));

        return bookingResponse;
    }

    @GetMapping("/booking/field/{field}")
    @ResponseStatus(HttpStatus.OK)
    List<BookingResponse> SearchBookingByField(@PathVariable Long field) {
        List<BookingResponse> bookingResponse = new ArrayList<>();
        List<Booking> foundBookings = bookingService.getBookingByField(field);

        for(Booking b : foundBookings) {
            bookingResponse.add(modelMapper.map(b, BookingResponse.class));
        }

        return bookingResponse;
    }

    @PutMapping("/booking/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookingResponse updateReport(@RequestBody BookingRequest bookingRequest, @PathVariable Long id) throws JSONException {
        Booking bookingSaved = bookingService.updateBooking(id, bookingRequest);

        BookingResponse bookingResponse = modelMapper.map(bookingSaved, BookingResponse.class);

        return bookingResponse;
    }

    @DeleteMapping("/booking/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }

}
