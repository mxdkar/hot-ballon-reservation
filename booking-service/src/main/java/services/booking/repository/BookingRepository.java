package services.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.booking.model.Booking;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUserId(Long userId);
}
