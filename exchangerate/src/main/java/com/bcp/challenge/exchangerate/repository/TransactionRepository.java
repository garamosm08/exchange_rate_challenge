package com.bcp.challenge.exchangerate.repository;

import com.bcp.challenge.exchangerate.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends JpaRepository<TransactionEntity, Long> {
}
