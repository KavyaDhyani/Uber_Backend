package com.kavya_dhyani.uber.controller;

import com.kavya_dhyani.uber.dto.*;
import com.kavya_dhyani.uber.model.User;
import com.kavya_dhyani.uber.service.UserMongoService;
import com.kavya_dhyani.uber.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        private UserMongoService userService;

        @Autowired
        private AuthenticationManager authManager;

        @Autowired
        private JwtUtil jwtUtil;

        @PostMapping("/register")
        public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest req) {
                User user = userService.register(
                                req.getUsername(),
                                req.getPassword(),
                                req.getRole());
                return new ResponseEntity<>(user, org.springframework.http.HttpStatus.CREATED);
        }

        @PostMapping("/login")
        public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest req) {

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                req.getUsername(), req.getPassword());

                authManager.authenticate(token);

                User user = userService
                                .loadUserByUsername(req
                                                .getUsername()) instanceof org.springframework.security.core.userdetails.User
                                                                ? null
                                                                : null;

                String jwt = jwtUtil.generateToken(req.getUsername(),
                                userService.loadUserByUsername(req.getUsername())
                                                .getAuthorities().iterator().next().getAuthority());

                return ResponseEntity.ok(Map.of("token", jwt));
        }
}
