package com.kavya_dhyani.uber.controller;

import com.kavya_dhyani.uber.dto.CreateRideRequest;
import com.kavya_dhyani.uber.model.Ride;
import com.kavya_dhyani.uber.service.RideMongoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RideController {

    @Autowired
    private RideMongoService rideService;

    @PostMapping("/rides")
    public ResponseEntity<Ride> createRide(@Valid @RequestBody CreateRideRequest req,
            Authentication auth) {
        Ride ride = rideService.createRide(
                auth.getName(),
                req.getPickupLocation(),
                req.getDropLocation());
        return new ResponseEntity<>(ride, org.springframework.http.HttpStatus.CREATED);
    }

    @PostMapping("/rides/{id}/complete")
    public ResponseEntity<Ride> complete(@PathVariable String id) {
        return ResponseEntity.ok(rideService.completeRide(id));
    }

    @GetMapping("/user/rides")
    public ResponseEntity<List<Ride>> myRides(Authentication auth) {
        return ResponseEntity.ok(rideService.getUserRides(auth.getName()));
    }
}
