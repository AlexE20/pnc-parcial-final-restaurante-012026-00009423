package com.uca.pncparcialfinalrestaurante.user.domain.dto;

import com.uca.pncparcialfinalrestaurante.restaurant.domain.entity.Restaurant;
import com.uca.pncparcialfinalrestaurante.role.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    private Restaurant restaurant; 
}
