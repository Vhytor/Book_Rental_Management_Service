package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.data.model.Book;
import com.bookkeeping.managementservice.data.model.Category;
import com.bookkeeping.managementservice.data.repository.BookRepository;
import com.bookkeeping.managementservice.exception.BookServiceException;
import com.bookkeeping.managementservice.payload.BookRequest;
import com.bookkeeping.managementservice.payload.BookResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public BookResponse registerBook(BookRequest bookRequest) throws BookServiceException {
        Optional<BookResponse> response = validateBookRequest(bookRequest);
        return response.orElseGet(()-> getBookResponse(bookRepository.save(Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .quantityOfBooksAvailable(Math.max(bookRequest.getQuantityOfBooksAvailable(), 0))
                .isbn(bookRequest.getIsbn())
                .datePublished(bookRequest.getDatePublished())
                .category(Category.valueOf(bookRequest.getCategory()))
                .build())));
    }

    private BookResponse getBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .quantityOfBooksAvailable(book.getQuantityOfBooksAvailable())
                .isbn(book.getIsbn())
                .datePublished(book.getDatePublished())
                .category(String.valueOf(book.getCategory()))
                .build();
    }

    private Optional<BookResponse> validateBookRequest(BookRequest bookRequest) {
        if (bookRepository.existsByTitleIsIgnoreCaseAndAuthorIgnoreCase(bookRequest.getTitle(), bookRequest.getAuthor())){
            return Optional.of(BookResponse.builder()
                    .message(" Book with title " + bookRequest.getTitle() + " and Author " + bookRequest.getAuthor() + " already exists ")
                    .build());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, BookRequest bookRequest) throws BookServiceException {
        Book existing = getBook(id);
        if (bookRequest.getTitle() != null && bookRequest.getAuthor() != null){
            validateBookRequest(bookRequest);
        }
        if (bookRequest.getAuthor() != null){
            existing.setAuthor(bookRequest.getAuthor());
        }
        if (bookRequest.getTitle() != null){
            existing.setTitle(bookRequest.getTitle());
        }
        if (bookRequest.getQuantityOfBooksAvailable() > 0){
            existing.setQuantityOfBooksAvailable(bookRequest.getQuantityOfBooksAvailable());
        }
        if (bookRequest.getIsbn() != null){
            existing.setIsbn(bookRequest.getIsbn());
        }
        if (bookRequest.getCategory() != null){
            existing.setCategory(Category.valueOf(bookRequest.getCategory()));
        }
        if (bookRequest.getDatePublished() != null){
            existing.setDatePublished(bookRequest.getDatePublished());
        }
        bookRepository.save(existing);
        return getBookResponse(existing);
    }
    private Book getBook(Long id) throws BookServiceException{
        return bookRepository.findById(id).orElseThrow(()-> new BookServiceException(" Book with id " + " does not exist "));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional()
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::getBookResponse).toList();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public BookResponse getBookById(Long id) throws BookServiceException {
        return getBookResponse(getBook(id));
    }

    @Override
    public void deleteBookById(Long id) throws BookServiceException {
        bookRepository.delete(getBook(id));
    }
}
