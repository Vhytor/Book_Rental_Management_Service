package com.bookkeeping.managementservice.data.repository;

import com.bookkeeping.managementservice.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

    boolean existsByTitleIsIgnoreCaseAndAuthorIgnoreCase(String title,String author);
}
