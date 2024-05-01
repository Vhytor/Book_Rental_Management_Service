package com.bookkeeping.managementservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String message;
    private Long id;
    private String title;
    private String author;
    private int quantityOfBooksAvailable;
    private String isbn;
    private String datePublished;
    private String category;

}
