package com.uca.pncparcialfinalrestaurante.auth.mapper;

import com.uca.pncparcialfinalrestaurante.auth.domain.dto.request.RegisterRequest;
import com.uca.pncparcialfinalrestaurante.user.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public User toEntity(RegisterRequest registerRequest){
        return User.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();
    }
}
