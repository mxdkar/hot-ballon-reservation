package services.booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import services.booking.dto.BookingRequest;
import services.booking.model.Booking;
import services.booking.service.BookingService;

@RestController
public class BookingController {

	private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
	 * Create a new booking.
	 *
	 * @param request The booking request containing userId and rideId.
	 * @return The created booking.
	 */
	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) {
		try {
			Booking booking = bookingService.createBooking(request.getUserId(), request.getRideId());
			return ResponseEntity.status(HttpStatus.CREATED).body(booking);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	/**
	 * Get bookings for a specific user.
	 *
	 * @param userId The ID of the user.
	 * @return List of bookings for the user.
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
		List<Booking> bookings = bookingService.getBookingsByUserId(userId);
		if (bookings.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(bookings);
	}
}

