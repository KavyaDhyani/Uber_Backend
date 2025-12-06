package com.kavya_dhyani.uber.controller;

import com.kavya_dhyani.uber.model.Ride;
import com.kavya_dhyani.uber.service.RideMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    @Autowired
    private RideMongoService rideService;

    @GetMapping("/rides/requests")
    public ResponseEntity<List<Ride>> pending() {
        return ResponseEntity.ok(rideService.getPendingRides());
    }

    @PostMapping("/rides/{id}/accept")
    public ResponseEntity<Ride> accept(@PathVariable String id, Authentication auth) {
        return ResponseEntity.ok(rideService.acceptRide(id, auth.getName()));
    }
}
