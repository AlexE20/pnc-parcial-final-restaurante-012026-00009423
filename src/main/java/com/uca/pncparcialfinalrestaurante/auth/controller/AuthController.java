package com.uca.pncparcialfinalrestaurante.auth.controller;

import com.uca.pncparcialfinalrestaurante.auth.domain.dto.request.LoginRequest;
import com.uca.pncparcialfinalrestaurante.auth.domain.dto.request.RefreshRequest;
import com.uca.pncparcialfinalrestaurante.auth.domain.dto.request.RegisterRequest;
import com.uca.pncparcialfinalrestaurante.auth.domain.dto.response.AuthResponse;
import com.uca.pncparcialfinalrestaurante.auth.service.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.ok(authService.refresh(refreshRequest.getRefreshToken()));
    }
}
