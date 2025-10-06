package com.service.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.domain.model.Credit;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreditResponse(
        @JsonProperty("numeroCredito")
        String creditNumber,
        @JsonProperty("numeroNfse")
        String nfseNumber,
        @JsonProperty("dataConstituicao")
        LocalDate incorporationDate,
        @JsonProperty("valorIssqn")
        BigDecimal issqnValue,
        @JsonProperty("tipoCredito")
        String creditType,
        @JsonProperty("simplesNacional")
        boolean simpleNational,
        @JsonProperty("aliquota")
        BigDecimal rate,
        @JsonProperty("valorFaturado")
        BigDecimal invoicedValue,
        @JsonProperty("valorDeducao")
        BigDecimal deductionValue,
        @JsonProperty("baseCalculo")
        BigDecimal calculationBase
) {

    public CreditResponse (Credit credit){
        this(
                credit.getCreditNumber(),
                credit.getNfseNumber(),
                credit.getIncorporationDate(),
                credit.getIssqnValue(),
                credit.getCreditType(),
                credit.isSimpleNational(),
                credit.getRate(),
                credit.getInvoicedValue(),
                credit.getDeductionValue(),
                credit.getCalculationBase()
        );
    }
}
