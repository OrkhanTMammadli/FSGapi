package com.project.FSGapi.DTO.Response;


import com.project.FSGapi.ENUM.AccountName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseJournal {
    private Long transactionId;
    private LocalDate entryDate;
    private String description;
    private Boolean isDebit;
    private AccountName accountName;
    private BigDecimal amount;
    private Long accountID;
}
