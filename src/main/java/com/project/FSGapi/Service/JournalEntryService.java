package com.project.FSGapi.Service;

import com.project.FSGapi.DTO.Request.RequestJournal;
import com.project.FSGapi.DTO.Response.ResponseJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JournalEntryService {
    ResponseJournal createJournalEntry(RequestJournal requestJournal);
    ResponseJournal updateJournalEntry(Long id, RequestJournal requestJournal);
    ResponseJournal getJournalEntryByID(Long id);
    void deleteJournalEntry(Long id);
    Page<ResponseJournal> getAllJournalEntries(Pageable pageable);
    Page<ResponseJournal> getJournalEntriesByAccounID(Long accountID, Pageable pageable);
}
