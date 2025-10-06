package com.service.domain.util;

import com.service.domain.model.Credit;
import com.service.domain.repository.CreditRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class InitialDataRunner implements CommandLineRunner {

    private final static Logger log = LogManager.getLogger(InitialDataRunner.class.getSimpleName());

    private final CreditRepository repository;

    public InitialDataRunner(CreditRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("--- Executando INSERTS no Banco de Dados ---");

        if (repository.count() == 0) {

            repository.saveAll(creditList());

            log.info(">>> Inserindo novos dados via CommandLineRunner.");
        } else {
            log.info(">>> Banco de dados já possui dados, pulando insert.");
        }

        log.info("-----------------------------------------------------");
    }

    public List<Credit> creditList() {
        var credit1 = build("123456", "7891011", LocalDate.parse("2024-02-25"), new BigDecimal("1500.75"), "ISSQN", true, new BigDecimal("5.0"), new BigDecimal("30000.00"), new BigDecimal("5000.00"), new BigDecimal("25000.00"));
        var credit2 = build("789012", "7891011", LocalDate.parse("2024-02-26"), new BigDecimal("1200.50"), "ISSQN", false, new BigDecimal("4.5"), new BigDecimal("25000.00"), new BigDecimal("4000.00"), new BigDecimal("21000.00"));
        var credit3 = build("654321", "1122334", LocalDate.parse("2024-01-15"), new BigDecimal("800.50"), "Outros", true, new BigDecimal("3.5"), new BigDecimal("20000.00"), new BigDecimal("3000.00"), new BigDecimal("17000.00"));

        return List.of(credit1, credit2, credit3);
    }

    public Credit build(
            String creditNumber,
            String nfseNumber,
            LocalDate incorporationDate,
            BigDecimal issqnValue,
            String creditType,
            Boolean simpleNational,
            BigDecimal rate,
            BigDecimal invoicedValue,
            BigDecimal deductionValue,
            BigDecimal calculationBase) {

        Credit credit = new Credit();

        credit.setCreditNumber(creditNumber);
        credit.setNfseNumber(nfseNumber);
        credit.setIncorporationDate(incorporationDate);
        credit.setIssqnValue(issqnValue);
        credit.setCreditType(creditType);
        credit.setSimpleNational(simpleNational);
        credit.setRate(rate);
        credit.setInvoicedValue(invoicedValue);
        credit.setDeductionValue(deductionValue);
        credit.setCalculationBase(calculationBase);

        return credit;
    }
}
