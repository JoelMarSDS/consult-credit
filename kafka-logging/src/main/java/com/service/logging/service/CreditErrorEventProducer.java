package com.service.logging.service;

import com.service.domain.event.dto.CreditLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//Produtor de mensagem de pesquisa com erro
@Service
public class CreditErrorEventProducer {

    private final static Logger log = LogManager.getLogger(CreditErrorEventProducer.class.getSimpleName());

    private static final String TOPIC_ERROR = "credit-consult-error";
    private final KafkaTemplate<String, CreditLogger> kafkaTemplate;

    public CreditErrorEventProducer(KafkaTemplate<String, CreditLogger> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCreditErrorEvent(CreditLogger credit){
        kafkaTemplate.send(TOPIC_ERROR, credit.uniqueKey(), credit);
        log.info("Evento de consulta de crédito enviado para Kafka: {}", credit);
    }
}