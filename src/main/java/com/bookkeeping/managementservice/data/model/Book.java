package com.bookkeeping.managementservice.data.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.antlr.v4.runtime.misc.NotNull;


import org.springframework.data.jpa.domain.AbstractAuditable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book extends AbstractAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    private int quantityOfBooksAvailable;

    @Column(length = 13)
    private String isbn;

    private String datePublished;

    @Enumerated(EnumType.STRING)
    private Category category;
}
