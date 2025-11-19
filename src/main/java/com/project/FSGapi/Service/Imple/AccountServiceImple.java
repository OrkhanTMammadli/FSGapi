package com.project.FSGapi.Service.Imple;

import com.project.FSGapi.DTO.Request.RequestAccount;
import com.project.FSGapi.DTO.Response.ResponseAccount;
import com.project.FSGapi.Entity.Account;
import com.project.FSGapi.Exception.AccountNotFoundE;
import com.project.FSGapi.Exception.AccountTypeAlreadyExistE;
import com.project.FSGapi.Mapper.AccountMapper;
import com.project.FSGapi.Repo.AccountRepository;
import com.project.FSGapi.Service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImple implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImple(AccountRepository accountRepository, AccountMapper accountMapper)
    {this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

//    _____________________________________________________
//    _____________________________________________________
//    Creating Method

    public ResponseAccount createAccount(RequestAccount requestAccount) {
        accountRepository.findByAccountType(requestAccount.getAccountType())
                .ifPresent(account -> {throw new AccountTypeAlreadyExistE
                        ("AccountType with name " + requestAccount.getAccountType() + " already exists");});

        Account account = accountMapper.toAccount(requestAccount);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toResponseAccount(savedAccount);
    }

//  ________________________________________________________________
//  ________________________________________________________________
//    Updating method

    public ResponseAccount updateAccount(Long id, RequestAccount requestAccount) {
        Account existingAccount = findAccountByID(id);
        accountRepository.findByAccountType(requestAccount.getAccountType())
                .ifPresent(account -> {throw new AccountTypeAlreadyExistE
                        ("AccountType with name " + requestAccount.getAccountType() + " already exists");});
        accountMapper.updateAccountFromRequest(requestAccount, existingAccount);
        Account updatedAccount = accountRepository.save(existingAccount);
        return accountMapper.toResponseAccount(updatedAccount);


    }

//  ________________________________________________
//  ________________________________________________
//  Get by ID method

    public ResponseAccount getAccountByID(Long id) {
        Account foundAccount =  accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundE("Account not found"));
        return accountMapper.toResponseAccount(foundAccount);
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

    public Page<ResponseAccount> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable).map(accountMapper::toResponseAccount);
    }
//  ________________________________________________
//  ________________________________________________
//  Find Account By ID method

    public Account findAccountByID(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundE("Account not found"));
    }
}

