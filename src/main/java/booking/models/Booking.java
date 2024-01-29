package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private Long id;
    private int idUser;
    private int idField;
    private int numPeople;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    public Booking() { this(null, null, 0, 0, null, null); }

    public Booking(String name, double price, int maxCapacity, String location, String description) {
        this(null, name, price, maxCapacity, location, description);
    }

    public Booking(Long id, String name, double price, int maxCapacity, String location, String description) {
        this.id = id;
        this.idUser = idUser;
        this.idField = idField;
        this.numPeople = numPeople;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdField() {
        return idField;
    }

    public void setIdField(int idField) {
        this.idField = idField;
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
