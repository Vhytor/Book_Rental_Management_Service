package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.data.model.Book;
import com.bookkeeping.managementservice.data.repository.BookRepository;
import com.bookkeeping.managementservice.data.repository.BorrowingRecordRepository;
import com.bookkeeping.managementservice.data.repository.PatronRepository;
import com.bookkeeping.managementservice.exception.BookServiceException;
import com.bookkeeping.managementservice.exception.BorrowingRecordServiceException;
import com.bookkeeping.managementservice.exception.PatronServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService{
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;


    @Override
    public void borrowBook(Long id, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException {
        Book book = getBook(id);
    }

    private Book getBook(Long id) {
    }

    @Override
    public void returnBook(Long id, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException {

    }
}
