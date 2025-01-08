package services.booking.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import services.booking.model.Ride;

@FeignClient(name = "ride-service")
public interface RideClient {

	@GetMapping("/{id}")
	Ride getRideById(@PathVariable Long id);

	@PutMapping("/{id}/decrement-capacity")
	void decrementRideCapacity(@PathVariable Long id);

	@GetMapping("/booking/{bookingId}")
	List<Ride> findByBooking(@PathVariable("bookingId") Long bookingId);
}
