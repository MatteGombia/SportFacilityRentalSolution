package booking.services;

import booking.models.Booking;
import booking.models.BookingEntity;
import booking.repositories.BookingRepository;
import booking.utils.FieldUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FieldUtils fieldUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Booking saveBooking(Booking booking) {

        BookingEntity bookingEntity = modelMapper.map(booking, BookingEntity.class);

        bookingEntity = bookingRepository.save(bookingEntity);

        Booking savedBooking = modelMapper.map(bookingEntity, Booking.class);

        return savedBooking;
    }

    @Override
    public Booking getBookingById(Long id) {

        //fieldRepository.getOne()
        return null;
    }

    @Override
    public List<Booking> getAllBooking() {
        return null;
    }

    @Override
    public void deleteBookingById(Long id) {

        try {
            bookingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Couldn't resolve ID: " + id);
        }

    }
}
