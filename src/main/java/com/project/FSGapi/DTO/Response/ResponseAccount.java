package com.project.FSGapi.DTO.Response;

import com.project.FSGapi.ENUM.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAccount {
    private Long id;
    private AccountType accountType;
    private String normalBalance;
}
