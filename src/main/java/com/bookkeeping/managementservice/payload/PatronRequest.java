package com.bookkeeping.managementservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronRequest {

    private String firstname;
    private String lastname;
    private String gender;
    private String phone_number;
    private String email;
}
