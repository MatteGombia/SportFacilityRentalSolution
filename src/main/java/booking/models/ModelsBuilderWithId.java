package booking.models;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class ModelsBuilderWithId extends ModelsBuilder implements Cloneable{
    protected Long id;

    public ModelsBuilderWithId(Long id, Long UserId, Long FieldId, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(UserId, FieldId, numPeople, date, timeStart, timeEnd);
        this.id = id;
    }
    public ModelsBuilderWithId(ModelsBuilderWithId mb) {
        super(mb.getUser(), mb.getField(), mb.getNumPeople(),mb.getDate(),mb.getTimeStart(),mb.getTimeEnd());
        setId(mb.getId());
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @Override
    public ModelsBuilderWithId clone() {
        return (ModelsBuilderWithId) super.clone();
    }
}
