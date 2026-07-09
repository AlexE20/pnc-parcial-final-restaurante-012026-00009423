package com.uca.pncparcialfinalrestaurante.order.domain.dto;

import com.uca.pncparcialfinalrestaurante.table.domain.entity.RestaurantTable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull(message = "Table is required")
    private RestaurantTable table;

    @NotEmpty(message = "Products list cannot be empty")
    private List<String> products;
}
