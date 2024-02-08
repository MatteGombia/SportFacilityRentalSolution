package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class ModelsBuilder implements Cloneable{
    protected Long user;
    protected Long field;
    protected int numPeople;
    protected LocalDate date;
    protected LocalTime timeStart;
    protected LocalTime timeEnd;
    protected ModelsBuilder(Long UserId, Long FieldId, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        this.user = UserId;
        this.field = FieldId;
        this.numPeople = numPeople;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getField() {
        return field;
    }

    public void setField(Long field) {
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

    @Override
    public ModelsBuilder clone() {
        try {
            return (ModelsBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
