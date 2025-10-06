package com.service.logging.listener;

import com.service.domain.event.CreditErrorEvent;
import com.service.logging.service.CreditErrorEventProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreditErrorKafkaPublisher {

    private final static Logger log = LogManager.getLogger(CreditErrorKafkaPublisher.class.getSimpleName());

    private final CreditErrorEventProducer creditErrorEventProducer;

    public CreditErrorKafkaPublisher(CreditErrorEventProducer creditErrorEventProducer) {
        this.creditErrorEventProducer = creditErrorEventProducer;
    }

    @EventListener
    public void handleCreditErrorEvent(CreditErrorEvent event) {
        log.info("Evento de domínio CreditProcessedEvent recebido::Erro. Enviando para Kafka...");
        creditErrorEventProducer.sendCreditErrorEvent(event.getCredit());
    }
}