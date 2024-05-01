package com.bookkeeping.managementservice.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingRecord  extends AbstractAuditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Patron patron;

    @JsonFormat(pattern = "dd-MM-yy HH-mm-ss" )
    private LocalDateTime borrowDate;

    @JsonFormat(pattern = "dd-MM-yy HH-mm-ss")
    private LocalDateTime returnDate;
}
