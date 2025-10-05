package com.service.domain.event.dto;

import java.time.LocalDateTime;


public record CreditLogger(
        LocalDateTime eventDate,
        String uniqueKey,
        String details,
        Boolean error
) {
}
