package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest extends ModelsBuilder{

    public BookingRequest(Long User, Long Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(User, Field, numPeople, date, timeStart, timeEnd);
    }

}
