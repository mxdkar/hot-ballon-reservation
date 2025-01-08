package services.ride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.ride.model.Ride;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
	List<Ride> findByLocation(String location);
}
