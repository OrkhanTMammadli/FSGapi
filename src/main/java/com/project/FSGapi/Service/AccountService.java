package com.project.FSGapi.Service;


import com.project.FSGapi.DTO.Request.RequestAccount;
import com.project.FSGapi.DTO.Response.ResponseAccount;
import com.project.FSGapi.Exception.AccountTypeAlreadyExistE;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    ResponseAccount createAccount (RequestAccount requestAccount) throws AccountTypeAlreadyExistE;
    ResponseAccount updateAccount (Long id, RequestAccount requestAccount);
    ResponseAccount getAccountByID(Long id);
    void  deleteAccount (Long id);
    List<ResponseAccount> getAllAccounts();
}
