package com.project.FSGapi.Repo;

import com.project.FSGapi.ENUM.AccountType;
import com.project.FSGapi.Entity.JournalEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    Optional<JournalEntry> findByTransactionId(Long id);;
    Page<JournalEntry> findByAccountId(Long accountId, Pageable pageable);
    @Query("SELECT COALESCE(SUM (je.amount), 0) FROM JournalEntry je JOIN je.account a WHERE a.accountType = :type ")
    BigDecimal sumTotalByAccountType(@Param("type") AccountType accountType);

}

