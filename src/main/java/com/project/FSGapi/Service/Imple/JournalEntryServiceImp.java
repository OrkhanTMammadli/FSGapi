package com.project.FSGapi.Service.Imple;


import com.project.FSGapi.DTO.IncomeStatementReport;
import com.project.FSGapi.DTO.Request.RequestJournal;
import com.project.FSGapi.DTO.Response.ResponseJournal;
import com.project.FSGapi.ENUM.AccountType;
import com.project.FSGapi.Entity.Account;
import com.project.FSGapi.Entity.JournalEntry;
import com.project.FSGapi.Exception.AccountNotFoundE;
import com.project.FSGapi.Exception.JournalEntryNotFoundE;
import com.project.FSGapi.Mapper.JournalEntryMapper;
import com.project.FSGapi.Repo.AccountRepository;
import com.project.FSGapi.Repo.JournalEntryRepository;
import com.project.FSGapi.Service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor

public class JournalEntryServiceImp implements JournalEntryService {
    private final JournalEntryRepository journalEntryRepository;
    private final AccountRepository accountRepository;
    private final JournalEntryMapper journalEntryMapper;

//    _____________________________________________________
//    _____________________________________________________
//    Creating Method
    public ResponseJournal createJournalEntry(RequestJournal requestJournal) {
        Account account = accountRepository.findById(requestJournal.getAccountID()).orElseThrow(() -> new AccountNotFoundE("Account not found" + requestJournal.getAccountID()));

        JournalEntry journalEntry = journalEntryMapper.toJournalEntry(requestJournal);
        journalEntry.setAccount(account);
        JournalEntry savedJournalEntry = journalEntryRepository.save(journalEntry);
        return journalEntryMapper.toResponseJournal(savedJournalEntry);
    }
//  ________________________________________________________________
//  ________________________________________________________________
//    Updating method

    public ResponseJournal updateJournalEntry(Long id, RequestJournal requestJournal) {

        JournalEntry existJournalEntry = journalEntryRepository.findByTransactionId(id)
                .orElseThrow(() -> new JournalEntryNotFoundE ("JournalEntry not found" + id));
        Account account = accountRepository.findById(requestJournal.getAccountID()).orElseThrow(() -> new AccountNotFoundE("Account not found" + requestJournal.getAccountID()));
        journalEntryMapper.updateJournalEntryFromRequest(requestJournal, existJournalEntry);
        existJournalEntry.setAccount(account);
        JournalEntry updatedJournalEntry = journalEntryRepository.save(existJournalEntry);
        return journalEntryMapper.toResponseJournal(updatedJournalEntry);

    }
//  ________________________________________________
//  ________________________________________________
//  Get by ID method

    public ResponseJournal getJournalEntryByID(Long id) {
        JournalEntry foundJournalEntry = journalEntryRepository.findByTransactionId(id)
                .orElseThrow(() -> new JournalEntryNotFoundE ("JournalEntry not found" + id));
        return journalEntryMapper.toResponseJournal(foundJournalEntry);
    }

//  ________________________________________________
//  ________________________________________________
//  Deleting method
    public void deleteJournalEntry(Long id) {
        JournalEntry journalEntry =  journalEntryRepository.findByTransactionId(id).orElseThrow(() -> new JournalEntryNotFoundE ("JournalEntry not found" + id));
        journalEntryRepository.delete(journalEntry);
   }

//  ________________________________________________
//  ________________________________________________
//  Get All method

    public Page<ResponseJournal> getAllJournalEntries(Pageable pageable) {
        return journalEntryRepository.findAll(pageable).map(journalEntryMapper::toResponseJournal);
    }

    public IncomeStatementReport generateReport () {
        BigDecimal revenue = journalEntryRepository.sumTotalByAccountType(AccountType.REVENUE);
        BigDecimal cogs = journalEntryRepository.sumTotalByAccountType(AccountType.COGS);
        BigDecimal grossProfit = revenue.subtract(cogs);
        BigDecimal totalExpenses = journalEntryRepository.sumTotalByAccountType(AccountType.EXPENSES);
        BigDecimal PBIT = grossProfit.subtract(totalExpenses);
        BigDecimal IncomeTax = journalEntryRepository.sumTotalByAccountType(AccountType.INCOME_TAX);
        BigDecimal InterestExpense = journalEntryRepository.sumTotalByAccountType(AccountType.INTEREST_EXPENSE);
        BigDecimal NetProfit = PBIT.subtract(IncomeTax).subtract(InterestExpense);


        return IncomeStatementReport.builder()
                .totalRevenue(revenue)
                .totalCostOfGoodsSold(cogs)
                .GrossProfit(grossProfit)
                .totalExpenses(totalExpenses)
                .PBIT(PBIT)
                .IncomeTax(IncomeTax)
                .InterestExpense(InterestExpense)
                .NetProfit(NetProfit)
                .generatedDate(LocalDate.now())
                .build();
    }

//  ________________________________________________
//  ________________________________________________
//  GetJE by Account ID method
    public Page<ResponseJournal> getJournalEntriesByAccounID (Long accountID, Pageable pageable) {
        accountRepository.findById(accountID).orElseThrow(() -> new AccountNotFoundE("Account not found" + accountID));
        return journalEntryRepository.findByAccountId(accountID, pageable).map(journalEntryMapper::toResponseJournal);
    }
}
