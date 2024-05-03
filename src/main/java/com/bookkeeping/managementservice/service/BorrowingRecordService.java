package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.exception.BookServiceException;
import com.bookkeeping.managementservice.exception.BorrowingRecordServiceException;
import com.bookkeeping.managementservice.exception.PatronServiceException;

public interface BorrowingRecordService {
    void borrowBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException;
    void returnBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException;
}
