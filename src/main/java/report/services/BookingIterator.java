package report.services;
import java.util.List;

public interface BookingIterator {
    boolean hasNext();
    BookingResponse next();
}
