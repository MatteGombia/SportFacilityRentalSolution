package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingResponse extends BookingRequest {

    private Long id;

    public BookingResponse() { this(null,0, 0, 0, null, null, null); }

    public BookingResponse(Long id, int idUser, int idField, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(idUser, idField, numPeople, date, timeStart, timeEnd);
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
