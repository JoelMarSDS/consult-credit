package com.service.domain.event;

import com.service.domain.event.dto.CreditLogger;
import org.springframework.context.ApplicationEvent;

public class CreditErrorEvent extends ApplicationEvent {

    private final CreditLogger credit;

    public CreditErrorEvent(Object source, CreditLogger credit) {
        super(source);
        this.credit = credit;
    }

    public CreditLogger getCredit() {
        return credit;
    }
}