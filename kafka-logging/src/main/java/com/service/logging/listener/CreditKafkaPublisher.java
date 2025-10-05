package com.service.logging.listener;

import com.service.domain.event.CreditProcessedEvent;
import com.service.logging.service.CreditEventProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// Observador de pesquisa com sucesso
@Component
public class CreditKafkaPublisher {

    private final static Logger log = LogManager.getLogger(CreditKafkaPublisher.class.getSimpleName());

    private final CreditEventProducer creditEventProducer;

    public CreditKafkaPublisher(CreditEventProducer creditEventProducer) {
        this.creditEventProducer = creditEventProducer;
    }

    @EventListener
    public void handleCreditProcessedEvent(CreditProcessedEvent event) {
        log.info("Evento de domínio CreditProcessedEvent recebido::Processado. Enviando para Kafka...");
        creditEventProducer.sendCreditEvent(event.getCredit());
    }
}