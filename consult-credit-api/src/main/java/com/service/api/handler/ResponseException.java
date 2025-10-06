package com.service.api.handler;

import org.springframework.http.HttpStatus;

public record ResponseException(
        int code,
        HttpStatus httpStatus,
        String message
) {
}
