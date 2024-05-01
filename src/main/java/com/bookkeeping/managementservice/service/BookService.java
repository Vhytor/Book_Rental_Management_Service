package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.exception.BookServiceException;
import com.bookkeeping.managementservice.payload.BookRequest;
import com.bookkeeping.managementservice.payload.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse registerBook(BookRequest bookRequest) throws BookServiceException;
    BookResponse updateBook(Long id, BookRequest bookRequest) throws BookServiceException;

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id) throws BookServiceException;

    void deleteBookById(Long id) throws BookServiceException;
}
