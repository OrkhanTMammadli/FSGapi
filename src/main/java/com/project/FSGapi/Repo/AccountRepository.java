package com.project.FSGapi.Repo;

import com.project.FSGapi.ENUM.AccountType;
import com.project.FSGapi.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountType(AccountType accountType);

}
