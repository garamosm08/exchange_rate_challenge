package com.bcp.challenge.exchangerate.repository;

import com.bcp.challenge.exchangerate.entity.AccountEntity;
import com.bcp.challenge.exchangerate.entity.ExchangeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeTypeEntity, Long> {

    @Query("from ExchangeTypeEntity ex where ex.foreignCurrency.id = :sourceCurrency or ex.foreignCurrency.id = :targetCurrency")
    ExchangeTypeEntity findExchangeTypeByForeignCurrency(@Param("sourceCurrency") Integer sourceCurrency, @Param("targetCurrency") Integer targetCurrency);
}
