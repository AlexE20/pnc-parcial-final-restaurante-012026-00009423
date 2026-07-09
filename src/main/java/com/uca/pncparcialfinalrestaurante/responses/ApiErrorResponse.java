package com.uca.pncparcialfinalrestaurante.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ApiErrorResponse {
    private Object message;
    private int status;
    private LocalDate time;
    private String uri;
}
