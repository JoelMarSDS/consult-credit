package com.service.domain.repository;

import com.service.domain.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repositório da tabela de crédito
@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    List<Credit> findAllByNfseNumber(String nfseNumber);
    Credit findByCreditNumber(String creditNumber);
}
