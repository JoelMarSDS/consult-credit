package com.service.domain.service;

import com.service.domain.event.CreditErrorEvent;
import com.service.domain.event.CreditProcessedEvent;
import com.service.domain.event.dto.CreditLogger;
import com.service.domain.exceptions.NotFoundException;
import com.service.domain.model.Credit;
import com.service.domain.repository.CreditRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ConsultCreditService {
    private final static Logger log = LogManager.getLogger(ConsultCreditService.class.getSimpleName());

    private final CreditRepository creditRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ConsultCreditService(CreditRepository creditRepository,ApplicationEventPublisher eventPublisher) {
        this.creditRepository = creditRepository;
        this.eventPublisher = eventPublisher;
    }

    // Serviço que consulta crédito por NFSe na base de dados
    public List<Credit> findCredits(String nfseNumber) {
        try {
            log.info("Pesquisando NFSe no banco.");
            var credits = creditRepository.findAllByNfseNumber(nfseNumber);

            if (credits.isEmpty()) {
                throw new NotFoundException("Não foi encontrado nenhum credito para o NFSE " + nfseNumber + "!");
            }
            log.info("Enviando evento de pesquisa por NFSe para o Kafka.");
            eventPublisher.publishEvent(new CreditProcessedEvent(this, new CreditLogger(LocalDateTime.now(), UUID.randomUUID().toString(), "Consult: ".concat(nfseNumber).concat(" - Size: ").concat(String.valueOf(credits.size())), false)));

            return credits;
        } catch (NotFoundException e){
            log.info("Enviando evento de erro da pesquisa por NFSe para o Kafka.");
            eventPublisher.publishEvent(new CreditErrorEvent(this, new CreditLogger(LocalDateTime.now(), UUID.randomUUID().toString(), "NotFound: ".concat(nfseNumber), true)));
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    // Serviço que consulta crédito por Número de identificação do credito
    public Credit findCredit(String creditNumber) {
        try {
            log.info("Pesquisando Numero de identificação do crédito no banco");
            var credit = creditRepository.findByCreditNumber(creditNumber);

            if (Objects.isNull(credit)) {
                throw new NotFoundException("Não foi encontrado nenhum credito com o número " + creditNumber + "!");
            }

            log.info("Enviando evento de pesquisa por Numero de Crédito para o Kafka.");
            eventPublisher.publishEvent(new CreditProcessedEvent(this, new CreditLogger(LocalDateTime.now(), UUID.randomUUID().toString(), "Consult: ".concat(creditNumber), false)));

            return credit;
        } catch (NotFoundException e){
            log.info("Enviando evento de erro da pesquisa por Numero de Crédito para o Kafka.");
            eventPublisher.publishEvent(new CreditErrorEvent(this, new CreditLogger(LocalDateTime.now(), UUID.randomUUID().toString(), "NotFound: ".concat(creditNumber), true)));
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
