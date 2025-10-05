package com.service.domain.event;

import com.service.domain.event.dto.CreditLogger;
import org.springframework.context.ApplicationEvent;

public class CreditProcessedEvent extends ApplicationEvent {

    private final CreditLogger credit;

    public CreditProcessedEvent(Object source, CreditLogger credit) {
        super(source);
        this.credit = credit;
    }

    public CreditLogger getCredit() {
        return credit;
    }
}