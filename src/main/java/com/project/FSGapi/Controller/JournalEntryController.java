package com.project.FSGapi.Controller;


import com.project.FSGapi.DTO.IncomeStatementReport;
import com.project.FSGapi.DTO.Request.RequestJournal;
import com.project.FSGapi.DTO.Response.ResponseJournal;
import com.project.FSGapi.Service.Imple.JournalEntryServiceImple;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journalEntry")
public class JournalEntryController {
    private final JournalEntryServiceImple journalEntryServiceImple;
    public JournalEntryController(JournalEntryServiceImple journalEntryServiceImple) {
        this.journalEntryServiceImple = journalEntryServiceImple;
    }

    @Operation(summary = "Adding a new journal entry")
    @PostMapping("/add")
    public ResponseEntity<ResponseJournal> addJournalEntry(@Valid @RequestBody RequestJournal requestJournal) {
        ResponseJournal Journal = journalEntryServiceImple.createJournalEntry(requestJournal);
        return new ResponseEntity<>(Journal, HttpStatus.CREATED);
    }

    @Operation(summary = "Finding the journal entry by Accout type ID")
    @GetMapping("/account/{accountID}")
    public ResponseEntity<List<ResponseJournal>> getJournalEntriesByAccounID (@PathVariable Long accountID) {
        List<ResponseJournal> Journal = journalEntryServiceImple.getJournalEntriesByAccounID(accountID);
        return ResponseEntity.ok(Journal);
    }

    @Operation(summary = "Finding all the journal entry records")
    @GetMapping("/all")
    public ResponseEntity<List<ResponseJournal>> getAllJournalEntries () {
        List<ResponseJournal> Journal = journalEntryServiceImple.getAllJournalEntries();
        return ResponseEntity.ok(Journal);
    }

    @Operation(summary = "Find the journal entry by ID")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseJournal> getJournalEntryById (@PathVariable Long id) {
        ResponseJournal responseJournal = journalEntryServiceImple.getJournalEntryByID(id);
        return new ResponseEntity<>(responseJournal, HttpStatus.OK);
    }

    @Operation(summary = "Delete the journal entry")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJournalEntryById (@PathVariable Long id) {
        journalEntryServiceImple.deleteJournalEntry(id);
    return new ResponseEntity<>("Deleted the requested journal entry",HttpStatus.OK);}

    @Operation(summary = "Updating the requested journal entry")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseJournal> updateJournalEntry (@Valid @RequestBody RequestJournal requestJournal, @PathVariable Long id) {
        ResponseJournal responseJournal = journalEntryServiceImple.updateJournalEntry(id, requestJournal);
        return new ResponseEntity<>(responseJournal, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Generate the Income Statement Report")
    @GetMapping("/report")
            public ResponseEntity<IncomeStatementReport> getIncomeStatement () {
        return ResponseEntity.ok(journalEntryServiceImple.generateReport());
    }

}
