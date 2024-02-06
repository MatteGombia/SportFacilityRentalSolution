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
    private int User;
    private int Field;
    private int numPeople;
    private LocalDate date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    public BookingEntity() {
        this(null,0, 0, 0, null, null, null);
    }

    public BookingEntity(Long id, int User, int Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this.User = User;
        this.Field = Field;
        this.numPeople = numPeople;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
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
