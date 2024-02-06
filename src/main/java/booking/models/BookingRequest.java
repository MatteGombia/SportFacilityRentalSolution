package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {
    private int user;
    private int field;
    private int numPeople;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    public BookingRequest(int User, int Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this.user = User;
        this.field = Field;
        this.numPeople = numPeople;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }
}
