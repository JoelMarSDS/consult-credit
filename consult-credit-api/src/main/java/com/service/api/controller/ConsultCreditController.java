package com.service.api.controller;

import com.service.api.dto.CreditResponse;
import com.service.domain.service.ConsultCreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/creditos")
public class ConsultCreditController {
    private final static Logger log = LogManager.getLogger(ConsultCreditController.class.getSimpleName());

    private final ConsultCreditService consultCreditService;

    public ConsultCreditController(ConsultCreditService consultCreditService) {
        this.consultCreditService = consultCreditService;
    }

    // Endpoint para consultar crédito por NFSe
    @Operation(summary = "Obtém uma lista de créditos", description = "Retorna uma lista de créditos constituídos com base no número da NFS-e.")
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditResponse>> findCredits(
            @Parameter(description = "Número identificador da NFS-e.", example = "7891011")
            @PathVariable("numeroNfse") String numberNfse) {
        log.info("Busca por Número de NFSe");
        var creditResponse = consultCreditService.findCredits(numberNfse);
        return ResponseEntity.ok(creditResponse.stream().map(CreditResponse::new).toList());
    }

    // Endpoint para consultar crédito por Número de identificação do crédito
    @Operation(summary = "Obtém um único credito.", description = "Retorna os detalhes de um crédito constituído específico com base no número do crédito constituído.")
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditResponse> findCredit(
            @Parameter(description = "Número identificador do crédito constituído.", example = "654321")
            @PathVariable("numeroCredito") String numberNfse) {
        log.info("Busca por Número de Crédito");
        var creditResponse = consultCreditService.findCredit(numberNfse);
        return ResponseEntity.ok(new CreditResponse(creditResponse));
    }
}
