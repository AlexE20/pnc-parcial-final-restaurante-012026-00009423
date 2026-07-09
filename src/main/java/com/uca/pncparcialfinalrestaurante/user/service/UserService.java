package com.uca.pncparcialfinalrestaurante.user.service;

import com.uca.pncparcialfinalrestaurante.user.domain.dto.UserRequest;
import com.uca.pncparcialfinalrestaurante.user.domain.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}
