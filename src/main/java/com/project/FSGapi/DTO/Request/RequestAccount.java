package com.project.FSGapi.DTO.Request;


import com.project.FSGapi.ENUM.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
    public class RequestAccount {

    private AccountType accountType;
    @NotBlank(message = "Normal balance is required: Debit or Credit")
    private String normalBalance;

}

