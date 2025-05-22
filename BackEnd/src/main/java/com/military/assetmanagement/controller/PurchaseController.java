
package com.military.assetmanagement.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {

    private static final List<Map<String, Object>> PURCHASES = new ArrayList<>();

    @PostMapping("/purchase")
    public ResponseEntity<String> recordPurchase(@RequestBody Map<String, Object> purchase) {
        purchase.put("timestamp", new Date());
        PURCHASES.add(purchase);
        return ResponseEntity.ok("Purchase recorded.");
    }

    @GetMapping("/purchase")
    public ResponseEntity<List<Map<String, Object>>> getPurchases(@RequestParam(required = false) String type) {
        if (type == null) return ResponseEntity.ok(PURCHASES);
        List<Map<String, Object>> filtered = PURCHASES.stream()
                .filter(p -> p.get("equipmentType").equals(type))
                .toList();
        return ResponseEntity.ok(filtered);
    }
}
