package services.ride.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ride.model.Ride;
import services.ride.service.RideService;

import java.util.List;

@RestController
public class RideController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RideController.class);

	private final RideService rideService;

	public RideController(RideService rideService) {
        this.rideService = rideService;
	}

	@GetMapping
	public List<Ride> getRides(@RequestParam(required = false) String location) {
		if (location != null) {
			return rideService.getRidesByLocation(location);
		}
		return rideService.getAllRides();
	}

	@PostMapping
	public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
		return ResponseEntity.ok(rideService.createRide(ride));
	}

	@PutMapping("/{id}/decrement-capacity")
	public ResponseEntity<Void> decrementCapacity(@PathVariable Long id) {
		Ride ride = rideService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Ride not found"));
		if (ride.getCapacity() > 0) {
			ride.setCapacity(ride.getCapacity() - 1);
			rideService.createRide(ride);
		} else {
			throw new IllegalArgumentException("No capacity left");
		}
		return ResponseEntity.ok().build();
	}
}
