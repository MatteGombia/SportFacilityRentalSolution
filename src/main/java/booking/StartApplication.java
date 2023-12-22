package booking;

import booking.models.BookingEntity;
import booking.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {

        SpringApplication.run(StartApplication.class, args);
    }

    @Bean
    @Profile("demo")
    CommandLineRunner initDatabase(BookingRepository bookingRepository) {
        return args -> {
            bookingRepository.save(new BookingEntity(1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0)));
            bookingRepository.save(new BookingEntity(1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0)));
            bookingRepository.save(new BookingEntity(1,1,5, LocalDate.of(2024, 12, 31), LocalTime.of(14, 30, 0), LocalTime.of(15, 30, 0)));
        };
    }
}
