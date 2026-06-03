package com.mecano.authservice.controller;

import com.mecano.authservice.dto.*;
import com.mecano.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/inscription/etape1")
    public ResponseEntity<EtapeResponse> etape1(@Valid @RequestBody Etape1Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.inscrireEtape1(request));
    }

    @PostMapping("/inscription/etape2")
    public ResponseEntity<EtapeResponse> etape2(@Valid @RequestBody Etape2Request request) {
        return ResponseEntity.ok(authService.inscrireEtape2(request));
    }

    @PostMapping("/inscription/etape3")
    public ResponseEntity<AuthResponse> etape3(@Valid @RequestBody Etape3Request request) {
        return ResponseEntity.ok(authService.inscrireEtape3(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}