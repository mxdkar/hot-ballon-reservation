package services.booking.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import services.booking.model.User;

@FeignClient(name = "user-service")
public interface UserClient {

	@GetMapping("/{id}")
	User getUserById(@PathVariable Long id);

	@GetMapping("/booking/{bookingId}")
	List<User> findByBooking(@PathVariable("bookingId") Long bookingId);
	
}
