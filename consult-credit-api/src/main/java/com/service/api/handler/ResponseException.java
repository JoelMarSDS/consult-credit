package com.service.api.handler;

import org.springframework.http.HttpStatus;

// DTO de resposta do tratamento de erro global
public record ResponseException(
        int code,
        HttpStatus httpStatus,
        String message
) {
}
