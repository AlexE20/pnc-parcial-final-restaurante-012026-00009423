package com.uca.pncparcialfinalrestaurante.auth.service;

import com.uca.pncparcialfinalrestaurante.auth.domain.dto.request.LoginRequest;
import com.uca.pncparcialfinalrestaurante.auth.domain.dto.request.RegisterRequest;
import com.uca.pncparcialfinalrestaurante.auth.domain.dto.response.AuthResponse;
import com.uca.pncparcialfinalrestaurante.auth.mapper.AuthMapper;
import com.uca.pncparcialfinalrestaurante.role.entity.Role;
import com.uca.pncparcialfinalrestaurante.role.entity.UserRole;
import com.uca.pncparcialfinalrestaurante.role.repository.RoleRepository;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import com.uca.pncparcialfinalrestaurante.user.domain.repository.UserRepository;
import com.uca.pncparcialfinalrestaurante.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + loginRequest.getUsername()));
        
        String accessToken = jwtUtil.generateToken(
                Map.of("role", user.getRole().getName(), "userId", user.getId()), user
        );
        String refreshToken = jwtUtil.generateRefreshToken(
                Map.of("role", user.getRole().getName(), "userId", user.getId()), user
        );

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public AuthResponse refresh(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        if (jwtUtil.isTokenValid(refreshToken, user)) {
            String newAccessToken = jwtUtil.generateToken(
                    Map.of("role", user.getRole().getName(), "userId", user.getId()), user
            );
            return AuthResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new RuntimeException("Invalid or expired refresh token");
        }
    }
}
