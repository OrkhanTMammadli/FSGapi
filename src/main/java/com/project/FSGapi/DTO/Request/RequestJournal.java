package com.project.FSGapi.DTO.Request;

import com.project.FSGapi.ENUM.AccountName;
import com.project.FSGapi.ENUM.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

    @Data
    public class RequestJournal {
        @PastOrPresent
        private LocalDate entryDate;
        @NotBlank(message = "Description is required")
        private String description;
        @NotNull(message = "True or False is required")
        private Boolean isDebit;
        private AccountName accountName;
        @Column(precision = 19, scale = 4)
        @NotNull(message = "Amount is required") @Min(1)
        private BigDecimal amount;
        @Enumerated(EnumType.STRING)
        private Long accountID;
}
