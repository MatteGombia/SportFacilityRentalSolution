package booking.controller;

import booking.models.BookingResponse;
import booking.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse newBooking(){
        return null;
    }
}
