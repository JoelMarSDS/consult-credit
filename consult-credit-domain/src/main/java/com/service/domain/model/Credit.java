package com.service.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

//Entidade representando a tabela no banco de dados
@Entity
@Table(name = "credito")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_credito")
    private String creditNumber;
    @Column(name = "numero_nfse")
    private String nfseNumber;
    @Column(name = "data_constituicao")
    private LocalDate incorporationDate;
    @Column(name = "valor_issqn")
    private BigDecimal issqnValue;
    @Column(name = "tipo_credito")
    private String creditType;
    @Column(name = "simples_nacional")
    private boolean simpleNational;
    @Column(name = "aliquota")
    private BigDecimal rate;
    @Column(name = "valor_faturado")
    private BigDecimal invoicedValue;
    @Column(name = "valor_deducao")
    private BigDecimal deductionValue;
    @Column(name = "base_calculo")
    private BigDecimal calculationBase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    public String getNfseNumber() {
        return nfseNumber;
    }

    public void setNfseNumber(String nfseNumber) {
        this.nfseNumber = nfseNumber;
    }

    public LocalDate getIncorporationDate() {
        return incorporationDate;
    }

    public void setIncorporationDate(LocalDate incorporationDate) {
        this.incorporationDate = incorporationDate;
    }

    public BigDecimal getIssqnValue() {
        return issqnValue;
    }

    public void setIssqnValue(BigDecimal issqnValue) {
        this.issqnValue = issqnValue;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public Boolean isSimpleNational() {
        return simpleNational;
    }

    public void setSimpleNational(Boolean simpleNational) {
        this.simpleNational = simpleNational;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getInvoicedValue() {
        return invoicedValue;
    }

    public void setInvoicedValue(BigDecimal invoicedValue) {
        this.invoicedValue = invoicedValue;
    }

    public BigDecimal getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(BigDecimal deductionValue) {
        this.deductionValue = deductionValue;
    }

    public BigDecimal getCalculationBase() {
        return calculationBase;
    }

    public void setCalculationBase(BigDecimal calculationBase) {
        this.calculationBase = calculationBase;
    }
}

