package com.service.logging.service;

import com.service.domain.event.dto.CreditLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//Produtor de mensagem de pesquisa com sucesso
@Service
public class CreditEventProducer {

    private final static Logger log = LogManager.getLogger(CreditEventProducer.class.getSimpleName());

    private static final String TOPIC_EVENT = "credit-consult-events";
    private final KafkaTemplate<String, CreditLogger> kafkaTemplate;

    public CreditEventProducer(KafkaTemplate<String, CreditLogger> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCreditEvent(CreditLogger credit){
        kafkaTemplate.send(TOPIC_EVENT, credit.uniqueKey(), credit);
        log.info("Evento de consulta de crédito enviado para Kafka: {}", credit);
    }
}