package report.services;
import java.util.List;

public class BookingListIterator implements BookingIterator {

    private final List<BookingResponse> bookings;
    private int position = 0;

    public BookingListIterator(List<BookingResponse> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean hasNext() {
        return position < bookings.size();
    }

    @Override
    public BookingResponse next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return bookings.get(position++);
    }
}
