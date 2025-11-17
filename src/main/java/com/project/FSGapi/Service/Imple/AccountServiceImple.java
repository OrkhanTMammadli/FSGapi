package com.project.FSGapi.Service.Imple;

import com.project.FSGapi.DTO.Request.RequestAccount;
import com.project.FSGapi.DTO.Response.ResponseAccount;
import com.project.FSGapi.Entity.Account;
import com.project.FSGapi.Exception.AccountNotFoundE;
import com.project.FSGapi.Exception.AccountTypeAlreadyExistE;
import com.project.FSGapi.Repo.AccountRepository;
import com.project.FSGapi.Service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImple implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImple(AccountRepository accountRepository)
    {this.accountRepository = accountRepository;}

//    _____________________________________________________
//    _____________________________________________________
//    Creating Method

    public ResponseAccount createAccount(RequestAccount requestAccount) {
        accountRepository.findByAccountType(requestAccount.getAccountType())
                .ifPresent(account -> {throw new AccountTypeAlreadyExistE
                        ("AccountType with name " + requestAccount.getAccountType() + " already exists");});

        Account account = MapToRequestAccount(requestAccount);
        accountRepository.save(account);
        return MapToResponseAccount(account);
    }

        private Account MapToRequestAccount(RequestAccount requestAccount) {
        Account account = new Account();
        account.setAccountType(requestAccount.getAccountType());
        account.setNormalBalance(requestAccount.getNormalBalance());
        return account;}

        private ResponseAccount MapToResponseAccount (Account account) {
        ResponseAccount responseAccount = new ResponseAccount();
        responseAccount.setId(account.getId());
        responseAccount.setAccountType(account.getAccountType());
        responseAccount.setNormalBalance(account.getNormalBalance());
        return responseAccount;}

//  ________________________________________________________________
//  ________________________________________________________________
//    Updating method

    public ResponseAccount updateAccount(Long id, RequestAccount requestAccount) {
        Account existingAccount = findAccountByID(id);
        accountRepository.findByAccountType(requestAccount.getAccountType())
                .ifPresent(account -> {throw new AccountTypeAlreadyExistE
                        ("AccountType with name " + requestAccount.getAccountType() + " already exists");});
        existingAccount.setAccountType(requestAccount.getAccountType());
        existingAccount.setNormalBalance(requestAccount.getNormalBalance());
        Account updatedAccount = accountRepository.save(existingAccount);
        return MapToResponseAccount(updatedAccount);
    }

//  ________________________________________________
//  ________________________________________________
//  Get by ID method

    public ResponseAccount getAccountByID(Long id) {
        Account foundAccount =  accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundE("Account not found"));
        return MapToResponseAccount(foundAccount);
    }

//  ________________________________________________
//  ________________________________________________
//  Deleting method

    public void deleteAccount(Long id) {
        Account account = findAccountByID(id);
        accountRepository.delete(account);}

//  ________________________________________________
//  ________________________________________________
//  Get All method

    public List<ResponseAccount> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::MapToResponseAccount).toList();
    }
//  ________________________________________________
//  ________________________________________________
//  Find Account By ID method

    public Account findAccountByID(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundE("Account not found"));
    }
}

