package com.faketrace.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    @Value("${faketrace.auth.username}")
    private String username;

    @Value("${faketrace.auth.password}")
    private String password;

    // Valida usuario y contrasena para el login basico del frontend.
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean credencialesValidas = username.equals(request.username()) && password.equals(request.password());

        if (!credencialesValidas) {
            return ResponseEntity.status(401).body(Map.of("message", "Usuario o contrasena incorrectos"));
        }

        return ResponseEntity.ok(Map.of(
                "authenticated", true,
                "username", request.username()
        ));
    }

    public record LoginRequest(String username, String password) {
    }
}
