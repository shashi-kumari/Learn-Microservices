package com.eazybytes.acounts.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eazybytes.acounts.entity.Account;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long accountNumber);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
