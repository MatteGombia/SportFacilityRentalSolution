package booking.services;

import booking.models.Booking;
import booking.models.BookingEntity;
import booking.models.BookingRequest;
import booking.models.BookingResponse;
import booking.repositories.BookingRepository;
import booking.utils.FieldUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
        if(booking.getTimeStart().isAfter(booking.getTimeEnd()))
            throw new IllegalArgumentException("The starting time can't be after the ending time");

        List<Booking> bookings = getBookingByField(booking.getField());
        for(Booking b : bookings){
            if(booking.getDate().compareTo(b.getDate()) == 0 && booking.getTimeStart().isAfter(b.getTimeStart()) && booking.getTimeStart().isBefore(b.getTimeEnd())){
                throw new IllegalArgumentException("Time start is already booked");
            }
            if(booking.getDate().compareTo(b.getDate()) == 0 && booking.getTimeEnd().isAfter(b.getTimeStart()) && booking.getTimeEnd().isBefore(b.getTimeEnd())){
                throw new IllegalArgumentException("Time end is already booked");
            }
            if(booking.getDate().compareTo(b.getDate()) == 0 && booking.getTimeStart().isBefore(b.getTimeStart()) && booking.getTimeEnd().isAfter(b.getTimeEnd())){
                throw new IllegalArgumentException("A part of the period is already booked");
            }
        }

        BookingEntity bookingEntity = modelMapper.map(booking, BookingEntity.class);

        bookingEntity = bookingRepository.save(bookingEntity);

        Booking savedBooking = modelMapper.map(bookingEntity, Booking.class);

        return savedBooking;
    }

    @Override
    public Booking getBookingById(Long id) {
        BookingEntity answer = bookingRepository.getOne(id);

        if(answer == null)
            throw new EntityNotFoundException();

        Booking savedBooking = modelMapper.map(answer, Booking.class);

        return savedBooking;
    }

    @Override
    public Booking updateBooking(Long id, BookingRequest booking){
        Booking bookingFound = getBookingById(id);

        bookingFound.setId(id);
        bookingFound.setUser(booking.getUser());
        bookingFound.setField(booking.getField());
        bookingFound.setDate(booking.getDate());
        bookingFound.setTimeStart(booking.getTimeStart());
        bookingFound.setTimeEnd(booking.getTimeEnd());

        bookingFound = saveBooking(bookingFound);

        return bookingFound;
    }


    @Override
    public List<Booking> getBookingByUser(Long user) {
        List<Booking> foundBookings = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        for(BookingEntity be : bookingEntities){
            if(be.getUser() == user)
                foundBookings.add(modelMapper.map(be, Booking.class));
        }
        return foundBookings;
    }

    @Override
    public List<Booking> getBookingByField(Long field) {
        List<Booking> foundBookings = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        for(BookingEntity be : bookingEntities){
            if(be.getField() == field)
                foundBookings.add(modelMapper.map(be, Booking.class));
        }
        return foundBookings;
    }

    @Override
    public List<Booking> getAllBooking() {
        List<Booking> foundBookings = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        for(BookingEntity be : bookingEntities){
            foundBookings.add(modelMapper.map(be, Booking.class));
        }
        return foundBookings;
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
