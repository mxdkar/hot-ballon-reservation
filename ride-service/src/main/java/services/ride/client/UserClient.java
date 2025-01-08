package services.ride.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import services.ride.model.User;

@FeignClient(name = "user-service")
public interface UserClient {

	@GetMapping("/ride/{rideId}")
	List<User> findByRide(@PathVariable("rideId") Long rideId);
	
}
