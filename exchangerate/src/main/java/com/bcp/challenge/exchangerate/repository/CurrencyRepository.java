package com.bcp.challenge.exchangerate.repository;

import com.bcp.challenge.exchangerate.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
}
