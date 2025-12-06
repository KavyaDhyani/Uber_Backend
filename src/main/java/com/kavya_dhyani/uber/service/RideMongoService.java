package com.kavya_dhyani.uber.service;

import com.kavya_dhyani.uber.model.Ride;
import com.kavya_dhyani.uber.repository.RideMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideMongoService {

    @Autowired
    private RideMongoRepository rideRepo;

    public Ride createRide(String userId, String pickup, String drop){
        Ride ride = new Ride();
        ride.setUserId(userId);
        ride.setpickupLocation(pickup);
        ride.setDropLocation(drop);
        ride.setStatus("REQUESTED");
        ride.setcreatedAt(LocalDateTime.now());
        return rideRepo.save(ride);
    }

    public List<Ride> getPendingRides(){
        return rideRepo.findByStatus("REQUESTED");
    }

    public Ride acceptRide(String rideId, String driverId){
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if(!ride.getStatus().equals("REQUESTED"))
            throw new RuntimeException("Ride already accepted");

        ride.setDriverId(driverId);
        ride.setStatus("ACCEPTED");
        return rideRepo.save(ride);
    }

    public Ride completeRide(String rideId){
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if(!ride.getStatus().equals("ACCEPTED"))
            throw new RuntimeException("Ride cannot be completed");

        ride.setStatus("COMPLETED");
        return rideRepo.save(ride);
    }

    public List<Ride> getUserRides(String userId){
        return rideRepo.findByUserId(userId);
    }
}
