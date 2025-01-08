package services.ride.service;

import org.springframework.stereotype.Service;
import services.ride.model.Ride;
import services.ride.repository.RideRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public List<Ride> getRidesByLocation(String location) {
        return rideRepository.findByLocation(location);
    }

    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Optional<Ride> findById(Long id) {
        return rideRepository.findById(id);
    }
}