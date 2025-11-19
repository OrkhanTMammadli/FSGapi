package com.project.FSGapi.Controller;


import com.project.FSGapi.DTO.IncomeStatementReport;
import com.project.FSGapi.DTO.Request.RequestJournal;
import com.project.FSGapi.DTO.Response.ResponseJournal;
import com.project.FSGapi.Service.Imple.JournalEntryServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journalEntry")
@RequiredArgsConstructor
public class JournalEntryController {
    private final JournalEntryServiceImp journalEntryServiceImp;


    @Operation(summary = "Adding a new journal entry")
    @PostMapping("/add")
    public ResponseEntity<ResponseJournal> addJournalEntry(@Valid @RequestBody RequestJournal requestJournal) {
        ResponseJournal Journal = journalEntryServiceImp.createJournalEntry(requestJournal);
        return new ResponseEntity<>(Journal, HttpStatus.CREATED);
    }

    @Operation(summary = "Finding the journal entry by Accout type ID")
    @GetMapping("/account/{accountID}")
    public ResponseEntity<Page<ResponseJournal>> getJournalEntriesByAccounID (@PathVariable Long accountID, Pageable pageable) {
        Page<ResponseJournal> Journal = journalEntryServiceImp.getJournalEntriesByAccounID(accountID, pageable);
        return ResponseEntity.ok(Journal);
    }

    @Operation(summary = "Finding all the journal entry records")
    @GetMapping("/all")
    public ResponseEntity<Page<ResponseJournal>> getAllJournalEntries (Pageable pageable) {
        Page<ResponseJournal> Journal = journalEntryServiceImp.getAllJournalEntries(pageable);
        return ResponseEntity.ok(Journal);
    }

    @Operation(summary = "Find the journal entry by ID")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseJournal> getJournalEntryById (@PathVariable Long id) {
        ResponseJournal responseJournal = journalEntryServiceImp.getJournalEntryByID(id);
        return new ResponseEntity<>(responseJournal, HttpStatus.OK);
    }

    @Operation(summary = "Delete the journal entry")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJournalEntryById (@PathVariable Long id) {
        journalEntryServiceImp.deleteJournalEntry(id);
    return new ResponseEntity<>("Deleted the requested journal entry",HttpStatus.OK);}

    @Operation(summary = "Updating the requested journal entry")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseJournal> updateJournalEntry (@Valid @RequestBody RequestJournal requestJournal, @PathVariable Long id) {
        ResponseJournal responseJournal = journalEntryServiceImp.updateJournalEntry(id, requestJournal);
        return new ResponseEntity<>(responseJournal, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Generate the Income Statement Report")
    @GetMapping("/report")
            public ResponseEntity<IncomeStatementReport> getIncomeStatement () {
        return ResponseEntity.ok(journalEntryServiceImp.generateReport());
    }

}
