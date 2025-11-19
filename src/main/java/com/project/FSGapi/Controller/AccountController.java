package com.project.FSGapi.Controller;


import com.project.FSGapi.DTO.Request.RequestAccount;
import com.project.FSGapi.DTO.Response.ResponseAccount;
import com.project.FSGapi.Service.Imple.AccountServiceImple;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountServiceImple AccountServiceImple;

    @Operation(summary = "Creating a new account")
    @PostMapping("/create")
    public ResponseEntity<ResponseAccount> createAccount(@Valid @RequestBody RequestAccount requestAccount) {
        ResponseAccount account = AccountServiceImple.createAccount(requestAccount);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    @Operation(summary = "Updating the account")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseAccount> updateAccount(@Valid @RequestBody RequestAccount requestAccount, @PathVariable Long id) {
        ResponseAccount responseAccount = AccountServiceImple.updateAccount(id, requestAccount);
        return new ResponseEntity<>(responseAccount, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Finding the account with ID")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseAccount> getAccountById(@PathVariable Long id) {
        ResponseAccount responseAccount = AccountServiceImple.getAccountByID(id);
        return new ResponseEntity<>(responseAccount, HttpStatus.OK);
    }

    @Operation(summary = "Show all accounts")
    @GetMapping("/all")
    public ResponseEntity<Page<ResponseAccount>> getAllAccounts(Pageable pageable) {
        Page<ResponseAccount> Account = AccountServiceImple.getAllAccounts(pageable);
        return new ResponseEntity<>(Account, HttpStatus.OK);
    }

    @Operation(summary = "Delete the account by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        AccountServiceImple.deleteAccount(id);
        return new ResponseEntity<>("Deleted the requested account",HttpStatus.ACCEPTED);
    }
}

