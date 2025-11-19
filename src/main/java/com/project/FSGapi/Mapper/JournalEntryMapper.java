package com.project.FSGapi.Mapper;


import com.project.FSGapi.DTO.Request.RequestJournal;
import com.project.FSGapi.DTO.Response.ResponseJournal;
import com.project.FSGapi.Entity.JournalEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JournalEntryMapper {
    @Mapping(source = "account.id", target = "accountID")
    ResponseJournal toResponseJournal(JournalEntry journalEntry);

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "transactionId", ignore = true)
    JournalEntry toJournalEntry(RequestJournal requestJournal);

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "transactionId", ignore = true)
    void updateJournalEntryFromRequest(RequestJournal requestJournal, @MappingTarget JournalEntry journalEntry);

}
