package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private Long id;
    private int User;
    private int Field;
    private int numPeople;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    public Booking() { this(null, 0,0,0,null,null,null); }

    public Booking(int User, int Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this(null, User, Field, numPeople, date, timeStart, timeEnd);
    }

    public Booking(Long id, int User, int Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this.id = id;
        this.User = User;
        this.Field = Field;
        this.numPeople = numPeople;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}
    public int getUser() {
        return User;
    }

    public void setUser(int user) {
        this.User = user;
    }

    public int getField() {
        return Field;
    }

    public void setField(int field) {
        this.Field = field;
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
