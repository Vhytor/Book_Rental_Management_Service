package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.data.model.Book;
import com.bookkeeping.managementservice.data.model.BorrowingRecord;
import com.bookkeeping.managementservice.data.model.Patron;
import com.bookkeeping.managementservice.data.repository.BookRepository;
import com.bookkeeping.managementservice.data.repository.BorrowingRecordRepository;
import com.bookkeeping.managementservice.data.repository.PatronRepository;
import com.bookkeeping.managementservice.exception.BookServiceException;
import com.bookkeeping.managementservice.exception.BorrowingRecordServiceException;
import com.bookkeeping.managementservice.exception.PatronServiceException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService{

    private final BorrowingRecordRepository borrowingRecordRepository;

    private final BookRepository bookRepository;

    private final PatronRepository patronRepository;


    public BorrowingRecordServiceImpl(BorrowingRecordRepository borrowingRecordRepository,BookRepository bookRepository,PatronRepository patronRepository){
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }


    @Override
    @Transactional
    public void borrowBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException {
        Book book = getBook(bookId);
        Patron patron = getPatron(patronId);

        if (borrowingRecordRepository.existsByBookAndPatronAndReturnDateIsNull(book,patron)){
            throw new BorrowingRecordServiceException(" You are yet to return a book: " + book.getTitle());
        }
        if (book.getQuantityOfBooksAvailable() < 1){
            throw new BookServiceException(" Book is available for borrowing: " + book.getTitle());
        }
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDateTime.now());

        book.setQuantityOfBooksAvailable(book.getQuantityOfBooksAvailable() - 1);

        bookRepository.save(book);
        borrowingRecordRepository.save(borrowingRecord);
    }

    private Patron getPatron(Long patronId) throws PatronServiceException {
        return patronRepository.findById(patronId).orElseThrow(()-> new PatronServiceException(" Patron with id not found: " + patronId));
    }

    private Book getBook(Long bookId) throws BookServiceException {
        return bookRepository.findById(bookId).orElseThrow(()-> new BookServiceException(" Book with id not found: " + bookId));
    }

    @Override
    @Transactional
    public void returnBook(Long bookId, Long patronId) throws BookServiceException, PatronServiceException, BorrowingRecordServiceException {

        Book book = getBook(bookId);
        Patron patron = getPatron(patronId);

        BorrowingRecord borrowingRecord = getBorrowingRecord(book,patron);

        borrowingRecord.setReturnDate(LocalDateTime.now());
        book.setQuantityOfBooksAvailable(book.getQuantityOfBooksAvailable() + 1);

        bookRepository.save(book);
        borrowingRecordRepository.save(borrowingRecord);
    }

    private BorrowingRecord getBorrowingRecord(Book book, Patron patron) throws BorrowingRecordServiceException {
        return borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book,patron).orElseThrow(()-> new BorrowingRecordServiceException(" Borrowing record not found "));
    }
}
