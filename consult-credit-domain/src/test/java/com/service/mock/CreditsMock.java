package com.service.mock;

import com.service.domain.model.Credit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreditsMock {

    public static Credit creditModel(){

        var credit = new Credit();

        credit.setCreditNumber("789012");
        credit.setNfseNumber("7891011");
        credit.setIncorporationDate(LocalDate.of(2024, 2,26));
        credit.setIssqnValue(new BigDecimal("1200.50"));
        credit.setCreditType("ISSQN");
        credit.setSimpleNational(Boolean.FALSE);
        credit.setRate(new BigDecimal("4.50"));
        credit.setInvoicedValue(new BigDecimal("25000.00"));
        credit.setDeductionValue(new BigDecimal("4000.00"));
        credit.setCalculationBase(new BigDecimal("21000.00"));

        return credit;
    }

    public static List<Credit> creditModelList(){
        return List.of(creditModel());
    }
}
