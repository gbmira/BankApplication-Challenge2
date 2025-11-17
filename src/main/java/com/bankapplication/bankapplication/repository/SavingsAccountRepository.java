package com.bankapplication.bankapplication.repository;

import com.bankapplication.bankapplication.model.Account;
import com.bankapplication.bankapplication.model.CheckingAccount;
import com.bankapplication.bankapplication.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    Optional<SavingsAccount> findByAccountNumber(String accountNumber);

    void deleteByAccountNumber(String accountNumber);
}
