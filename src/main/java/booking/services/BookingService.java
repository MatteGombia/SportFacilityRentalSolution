package booking.services;

import booking.models.Booking;

import java.util.List;

public interface BookingService {

    //Interact with the repository (database) to perform
    //CRUD operations on fields.

    //Convert between different data representations, if necessary.

    //Handle exceptions and errors that may occur during business logic execution or data access.
    //Convert exceptions into meaningful error messages for clients.

    List<Booking> getBookingByUser(Long user);
    List<Booking> getBookingByField(Long field);
    Booking saveBooking(Booking booking);

    List<Booking> getAllBooking();

    Booking getBookingById(Long id);

    void deleteBookingById(Long id);
}
