package com.project.FSGapi.Repo;

import com.project.FSGapi.ENUM.AccountType;
import com.project.FSGapi.Entity.JournalEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findByAccountId(Long accountId);
    Optional<JournalEntry> findByTransactionId(Long id);;
    Page<JournalEntry> findByAccountId(Long accountId, Pageable pageable);
    @Query("SELECT COALESCE(SUM (t.amount), 0) FROM JournalEntry t JOIN t.account a WHERE a.accountType = :type ")
    BigDecimal sumTotalByAccountType(@Param("type") AccountType accountType);

}

