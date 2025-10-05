package com.service.domain.service;

import com.service.domain.event.CreditProcessedEvent;
import com.service.domain.event.dto.CreditLogger;
import com.service.domain.repository.CreditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.service.mock.CreditsMock.creditModel;
import static com.service.mock.CreditsMock.creditModelList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultCreditServiceTest {

    @InjectMocks
    private ConsultCreditService completeClaimService;

    @Mock
    private CreditRepository creditRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findCredits() {
        var requestDTO = creditModelList();

        when(creditRepository.findAllByNfseNumber(anyString())).thenReturn(requestDTO);
        doNothing().when(eventPublisher).publishEvent(any(CreditProcessedEvent.class));

        var response = completeClaimService.findCredits(anyString());

        assertNotNull(response);
        assertEquals(response.getFirst().getCreditNumber(), requestDTO.getFirst().getCreditNumber());
    }

    @Test
    void findCredit() {
        var requestDTO = creditModel();

        when(creditRepository.findByCreditNumber(anyString())).thenReturn(requestDTO);
        doNothing().when(eventPublisher).publishEvent(any(CreditProcessedEvent.class));

        var response = completeClaimService.findCredit(anyString());

        assertNotNull(response);
        assertEquals(response.getNfseNumber(), requestDTO.getNfseNumber());
    }
}