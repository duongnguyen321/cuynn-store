package com.zmen.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/simple")
@Tag(name = "Simple API", description = "Simple test APIs")
@CrossOrigin(origins = "*")
public class SimpleController {
    
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Check if the API is running")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "API is running successfully");
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/info")
    @Operation(summary = "Get API info", description = "Get basic information about the API")
    public ResponseEntity<Map<String, Object>> getInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "ZMEN E-commerce Backend API");
        response.put("version", "1.0.0");
        response.put("description", "Backend API for ZMEN E-commerce platform");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/echo")
    @Operation(summary = "Echo message", description = "Echo back the provided message")
    public ResponseEntity<Map<String, Object>> echo(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("original", request.get("message"));
        response.put("echo", "Echo: " + request.get("message"));
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
}

