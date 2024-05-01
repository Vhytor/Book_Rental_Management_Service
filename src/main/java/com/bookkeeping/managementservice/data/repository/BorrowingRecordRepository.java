package com.bookkeeping.managementservice.data.repository;

import com.bookkeeping.managementservice.data.model.Book;
import com.bookkeeping.managementservice.data.model.BorrowingRecord;
import com.bookkeeping.managementservice.data.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {

    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

    boolean existsByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
}
