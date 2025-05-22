
package com.military.assetmanagement.controller;

import com.military.assetmanagement.model.User;
import com.military.assetmanagement.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private static final List<User> USERS = List.of(
            new User(1, "admin", "admin123", "admin", 0),
            new User(2, "commander", "cmd123", "commander", 1),
            new User(3, "logistics", "log123", "logistics", 0)
    );

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        Optional<User> userOpt = USERS.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();

        if (userOpt.isEmpty()) return ResponseEntity.status(401).build();

        String token = JwtUtil.generateToken(userOpt.get());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
