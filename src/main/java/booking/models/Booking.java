package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking extends ModelsBuilderWithId{

    public Booking() { this(null, null,null,0,null,null,null); }

    public Booking(Long User, Long Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this(null, User, Field, numPeople, date, timeStart, timeEnd);
    }

    public Booking(Long id, Long User, Long Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(id, User, Field, numPeople, date, timeStart, timeEnd);
    }
}
