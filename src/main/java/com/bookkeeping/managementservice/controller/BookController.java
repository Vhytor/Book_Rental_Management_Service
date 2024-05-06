package com.bookkeeping.managementservice.controller;

import com.bookkeeping.managementservice.exception.BookServiceException;
import com.bookkeeping.managementservice.payload.BookRequest;
import com.bookkeeping.managementservice.payload.BookResponse;
import com.bookkeeping.managementservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public ResponseEntity<BookResponse> registerBook(@RequestBody BookRequest bookRequest) throws BookServiceException {
        BookResponse bookResponse = bookService.registerBook(bookRequest);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id,@RequestBody BookRequest bookRequest) throws BookServiceException {
        BookResponse bookResponse = bookService.updateBook(id, bookRequest);
        return new ResponseEntity<>(bookResponse,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return new ResponseEntity<>(bookResponses,HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) throws BookServiceException {
        BookResponse bookResponse = bookService.getBookById(id);
        return new ResponseEntity<>(bookResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) throws BookServiceException {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }



}
