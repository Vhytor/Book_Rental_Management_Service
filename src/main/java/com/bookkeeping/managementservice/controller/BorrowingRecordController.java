package com.bookkeeping.managementservice.controller;

import com.bookkeeping.managementservice.data.model.BorrowingRecord;
import com.bookkeeping.managementservice.exception.BookServiceException;
import com.bookkeeping.managementservice.exception.BorrowingRecordServiceException;
import com.bookkeeping.managementservice.exception.PatronServiceException;
import com.bookkeeping.managementservice.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController{
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId,@PathVariable Long patronId) throws BorrowingRecordServiceException, PatronServiceException, BookServiceException {
        borrowingRecordService.borrowBook(bookId,patronId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId,@PathVariable Long patronId) throws BorrowingRecordServiceException, PatronServiceException, BookServiceException {
        borrowingRecordService.returnBook(bookId,patronId);
        return ResponseEntity.ok().build();
    }
}
