package com.project.FSGapi.Mapper;

import com.project.FSGapi.DTO.Request.RequestAccount;
import com.project.FSGapi.DTO.Response.ResponseAccount;
import com.project.FSGapi.Entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = JournalEntryMapper.class)
public interface AccountMapper {
    ResponseAccount toResponseAccount (Account account);
    Account toAccount (RequestAccount requestAccount);
    //void updateAccountFromRequest (RequestAccount requestAccount, @MappingTarget Account account);
}
