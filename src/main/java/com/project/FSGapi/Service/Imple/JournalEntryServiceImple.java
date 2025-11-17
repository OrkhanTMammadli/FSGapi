package com.project.FSGapi.Service.Imple;


import com.project.FSGapi.DTO.Request.RequestJournal;
import com.project.FSGapi.DTO.Response.ResponseJournal;
import com.project.FSGapi.Entity.Account;
import com.project.FSGapi.Entity.JournalEntry;
import com.project.FSGapi.Exception.AccountNotFoundE;
import com.project.FSGapi.Exception.JournalEntryNotFoundE;
import com.project.FSGapi.Repo.AccountRepository;
import com.project.FSGapi.Repo.JournalEntryRepository;
import com.project.FSGapi.Service.JournalEntryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryServiceImple implements JournalEntryService {
    private final JournalEntryRepository journalEntryRepository;
    private final AccountRepository accountRepository;

    public JournalEntryServiceImple(JournalEntryRepository journalEntryRepository, AccountRepository accountRepository) {
        this.journalEntryRepository = journalEntryRepository;
        this.accountRepository = accountRepository;
    }
//    _____________________________________________________
//    _____________________________________________________
//    Creating Method
    public ResponseJournal createJournalEntry(RequestJournal requestJournal) {
        JournalEntry journalEntry = MapToJournalEntry(requestJournal);
        journalEntryRepository.save(journalEntry);
        return MapToResponseJournal(journalEntry);
    }
        private JournalEntry MapToJournalEntry(RequestJournal requestJournal) {
            Account Account = accountRepository.findById(requestJournal.getAccountID())
                    .orElseThrow(() -> new AccountNotFoundE("Account not found" + requestJournal.getAccountID()));
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setEntryDate(requestJournal.getEntryDate());
        journalEntry.setIsDebit(requestJournal.getIsDebit());
        journalEntry.setAccountName(requestJournal.getAccountName());
        journalEntry.setDescription(requestJournal.getDescription());
        journalEntry.setAmount(requestJournal.getAmount());
        journalEntry.setAccount(Account);
        return journalEntry;}


        private ResponseJournal MapToResponseJournal(JournalEntry journalEntry) {
        ResponseJournal responseJournal = new ResponseJournal();
        responseJournal.setTransactionId(journalEntry.getTransactionId());
        responseJournal.setEntryDate(journalEntry.getEntryDate());
        responseJournal.setIsDebit(journalEntry.getIsDebit());
        responseJournal.setAccountName(journalEntry.getAccountName());
        responseJournal.setDescription(journalEntry.getDescription());
        responseJournal.setAmount(journalEntry.getAmount());
        responseJournal.setAccountID(journalEntry.getAccount().getId());
        return responseJournal;
    }
//  ________________________________________________________________
//  ________________________________________________________________
//    Updating method

    public ResponseJournal updateJournalEntry(Long id, RequestJournal requestJournal) {

        JournalEntry existJournalEntry = journalEntryRepository.findByTransactionId(id)
                .orElseThrow(() -> new JournalEntryNotFoundE ("JournalEntry not found" + id));
        existJournalEntry.setEntryDate(requestJournal.getEntryDate());
        existJournalEntry.setIsDebit(requestJournal.getIsDebit());
        existJournalEntry.setAccountName(requestJournal.getAccountName());
        existJournalEntry.setDescription(requestJournal.getDescription());
        existJournalEntry.setAmount(requestJournal.getAmount());
        JournalEntry updatedJournalEntry = journalEntryRepository.save(existJournalEntry);
        return MapToResponseJournal(updatedJournalEntry);
    }
//  ________________________________________________
//  ________________________________________________
//  Get by ID method

    public ResponseJournal getJournalEntryByID(Long id) {
        JournalEntry foundJournalEntry = journalEntryRepository.findByTransactionId(id)
                .orElseThrow(() -> new JournalEntryNotFoundE ("JournalEntry not found" + id));
        return MapToResponseJournal(foundJournalEntry);
    }

//  ________________________________________________
//  ________________________________________________
//  Deleting method
    public void deleteJournalEntry(Long id) {
//        JournalEntry journalEntry =  journalEntryRepository.findById(id);
//        journalEntryRepository.delete(journalEntry);
   }

//  ________________________________________________
//  ________________________________________________
//  Get All method
    public List<ResponseJournal> getAllJournalEntries() {
        return journalEntryRepository.findAll().stream().map(this::MapToResponseJournal).toList();
    }
//  ________________________________________________
//  ________________________________________________
//  GetJE by Account ID method
    public List<ResponseJournal> getJournalEntriesByAccounID (Long accountID) {
        accountRepository.findById(accountID).orElseThrow(() -> new AccountNotFoundE("Account not found" + accountID));

        List<JournalEntry> journalEntryList = journalEntryRepository.findByAccountId(accountID);

        return journalEntryList.stream().map(journalEntry ->  {
            ResponseJournal responseJournal = new ResponseJournal();
            responseJournal.setTransactionId(journalEntry.getTransactionId());
            responseJournal.setEntryDate(journalEntry.getEntryDate());
            responseJournal.setIsDebit(journalEntry.getIsDebit());
            responseJournal.setAmount(journalEntry.getAmount());
            responseJournal.setAccountName(journalEntry.getAccountName());
            responseJournal.setDescription(journalEntry.getDescription());
            if (journalEntry.getAccount() != null) {
                responseJournal.setAccountID(journalEntry.getAccount().getId());}
            return responseJournal;
        }).toList();
    }
}
