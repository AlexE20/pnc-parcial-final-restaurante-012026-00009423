package com.uca.pncparcialfinalrestaurante.table.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTableResponse {
    private Long id;
    private Long restaurantId;
    private String restaurantName;
    private Integer capacity;
    private String status;
}
