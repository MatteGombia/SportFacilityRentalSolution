package booking.repositories;

import booking.models.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
