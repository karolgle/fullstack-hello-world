package com.example.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
// Vite serves the frontend on port 5173, so allow requests from that origin
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {
    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello from Java backend!");
    }
}
