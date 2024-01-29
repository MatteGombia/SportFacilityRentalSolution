package booking.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingEntity {
    @Id
    @GeneratedValue
    private Long id;
    private int idUser;
    private int idField;
    private int numPeople;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    public BookingEntity() {
        this(null,0, 0, 0, null, null, null);
    }

    public BookingEntity(Long id, int idUser, int idField, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this.idUser = idUser;
        this.idField = idField;
        this.numPeople = numPeople;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.id = id;
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