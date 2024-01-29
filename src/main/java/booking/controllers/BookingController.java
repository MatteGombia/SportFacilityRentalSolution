package booking.controllers;

import booking.models.Booking;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    ModelMapper modelMapper;

    BookingResponse allBooking(@RequestBody BookingRequest bookingRequest) {
        return null;
    }
    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    BookingResponse newBooking(@RequestBody BookingRequest bookingRequest) {

        Booking booking = modelMapper.map(bookingRequest, Booking.class);

        Booking savedBooking = bookingService.saveBooking(booking);

        BookingResponse bookingResponse = modelMapper.map(savedBooking, BookingResponse.class);

        return bookingResponse;
    }

    @GetMapping("/booking/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookingResponse findOne(@PathVariable Long id) {
        return null;
    }

    BookingResponse updateBooking(@RequestBody BookingRequest bookingRequest, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/booking/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBooking(@PathVariable Long id) {
    }

}
