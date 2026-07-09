package com.uca.pncparcialfinalrestaurante.restaurant.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {

    @NotBlank(message = "The restaurant name cannot be blank")
    private String name;

    @NotBlank(message = "The address cannot be blank")
    private String address;
}
