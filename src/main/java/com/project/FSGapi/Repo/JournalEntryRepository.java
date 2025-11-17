package com.project.FSGapi.Repo;

import com.project.FSGapi.Entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    // You can add custom query methods here if needed
    List<JournalEntry> findByAccountId(Long accountId);
    Optional<JournalEntry> findByTransactionId(Long id);


}

