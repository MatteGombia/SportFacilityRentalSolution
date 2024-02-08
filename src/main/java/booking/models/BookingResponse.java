package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingResponse extends ModelsBuilderWithId  {
    public BookingResponse() { this(null,null, null, 0, null, null, null); }

    public BookingResponse(Long id, Long UserId, Long FieldId, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(id, UserId, FieldId, numPeople, date, timeStart, timeEnd);
    }
}
