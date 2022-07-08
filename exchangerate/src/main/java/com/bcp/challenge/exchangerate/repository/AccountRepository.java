package com.bcp.challenge.exchangerate.repository;

import com.bcp.challenge.exchangerate.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("from AccountEntity a where a.user.username = :username")
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<AccountEntity> findAccountEntitiesByCustomerCode(@Param("username") String username);
}
