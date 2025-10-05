package com.service.api.controller;

import com.service.domain.service.ConsultCreditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static com.service.mock.CreditsMock.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConsultCreditControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ConsultCreditController consultCreditController;

    @Mock
    private ConsultCreditService consultCreditService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(consultCreditController).build();
    }

    @Test
    void findCredits() throws Exception {
        var requestDTO = creditList();
        var cancelClaim = creditModelList();

        System.out.println(LocalDateTime.now());
        when(consultCreditService.findCredits("7891011"))
                .thenReturn(cancelClaim);

        mockMvc.perform(get("/creditos/7891011")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito").value(requestDTO.getFirst().creditNumber()))
                .andReturn();
    }

    @Test
    void findCredit() throws Exception {
        var requestDTO = credit();
        var cancelClaim = creditModel();

        System.out.println(LocalDateTime.now());
        when(consultCreditService.findCredit("789012"))
                .thenReturn(cancelClaim);

        mockMvc.perform(get("/creditos/credito/789012")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroNfse").value(requestDTO.nfseNumber()))
                .andReturn();
    }
}