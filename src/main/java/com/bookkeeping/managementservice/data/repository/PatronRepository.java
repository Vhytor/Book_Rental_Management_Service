package com.bookkeeping.managementservice.data.repository;

import com.bookkeeping.managementservice.data.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron,Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone_number(String phone_number);
}
