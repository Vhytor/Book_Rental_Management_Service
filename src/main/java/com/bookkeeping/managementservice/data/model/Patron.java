package com.bookkeeping.managementservice.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

 @Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Patron extends AbstractAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @Column(unique = true)
    @Email(message = "Invalid email format" )
    @NotNull(message = "Email cannot be null" )
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true, length = 11)
    private String phone_number;

    private String address;


}
