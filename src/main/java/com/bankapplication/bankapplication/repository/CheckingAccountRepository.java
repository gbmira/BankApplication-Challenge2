package com.bankapplication.bankapplication.repository;

import com.bankapplication.bankapplication.model.Account;
import com.bankapplication.bankapplication.model.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

    Optional<CheckingAccount> findByAccountNumber(String accountNumber);

    void deleteByAccountNumber(String accountNumber);
}
