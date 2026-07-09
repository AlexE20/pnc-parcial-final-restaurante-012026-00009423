package com.uca.pncparcialfinalrestaurante.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private String clientUsername;
    private Long tableId;
    private Long restaurantId;
    private String restaurantName;
    private String status;
    private List<String> products;
}
