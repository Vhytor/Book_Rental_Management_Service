package com.bookkeeping.managementservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronResponse {
    private String message;
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String gender;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
