
package com.military.assetmanagement.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> data = new HashMap<>();
        data.put("openingBalance", 100);
        data.put("closingBalance", 120);
        data.put("netMovement", 20);
        data.put("assigned", 5);
        data.put("expended", 2);
        return ResponseEntity.ok(data);
    }
}
