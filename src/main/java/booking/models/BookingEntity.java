package booking.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BookingEntity extends ModelsBuilder{
    @Id
    @GeneratedValue
    private Long id;
    public BookingEntity() {
        this(null,null, null, 0, null, null, null);
    }

    public BookingEntity(Long id, Long User, Long Field, int numPeople, LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        super(User, Field, numPeople, date, timeStart, timeEnd);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
