package services.booking.service;

import org.springframework.stereotype.Service;
import services.booking.client.RideClient;
import services.booking.client.UserClient;
import services.booking.model.Booking;
import services.booking.model.Ride;
import services.booking.model.User;
import services.booking.repository.BookingRepository;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final RideClient rideClient;

    public BookingService(BookingRepository bookingRepository, UserClient userClient, RideClient rideClient) {
        this.bookingRepository = bookingRepository;
        this.userClient = userClient;
        this.rideClient = rideClient;
    }

    public Booking createBooking(Long userId, Long rideId) {
        // Fetch user details from UserService
        User user = userClient.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Fetch ride details from RideService
        Ride ride = rideClient.getRideById(rideId);
        if (ride == null || ride.getCapacity() <= 0) {
            throw new IllegalArgumentException("Ride not available");
        }

        // Decrement ride capacity in RideService
        rideClient.decrementRideCapacity(rideId);

        // Save booking
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setRideId(rideId);
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
