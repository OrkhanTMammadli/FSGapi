package com.project.FSGapi.Entity;


import com.project.FSGapi.ENUM.AccountName;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data

public class JournalEntry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDate entryDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private AccountName accountName;
    private Boolean isDebit;
    @Column(precision = 19, scale = 4)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
