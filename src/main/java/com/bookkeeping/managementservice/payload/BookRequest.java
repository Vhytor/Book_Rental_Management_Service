package com.bookkeeping.managementservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {

  private String email;
  private String title;
  private String author;
  private int quantityOfBooksAvailable;
  private String isbn;
  private String datePublished;
  private String category;
}
