package com.uca.pncparcialfinalrestaurante.table.domain.dto;

import com.uca.pncparcialfinalrestaurante.restaurant.domain.entity.Restaurant;
import jakarta.validation.constraints.Min;
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
public class RestaurantTableRequest {

    @NotNull(message = "The restaurant is required")
    private Restaurant restaurant;

    @NotNull(message = "The table capacity is required")
    @Min(value = 1, message = "Table capacity must be at least 1")
    private Integer capacity;

    @NotBlank(message = "The table status is required")
    private String status;
}
