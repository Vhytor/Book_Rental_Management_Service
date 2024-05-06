package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.exception.PatronServiceException;
import com.bookkeeping.managementservice.payload.PatronRequest;
import com.bookkeeping.managementservice.payload.PatronResponse;

import java.util.List;

public interface PatronService {
    PatronResponse registerPatron(PatronRequest patronRequest) throws PatronServiceException;
    PatronResponse updatePatron(Long Id,PatronRequest patronRequest ) throws PatronServiceException;
    List<PatronResponse> getAllPatron();
    PatronResponse getPatronById(Long id) throws PatronServiceException;
    void deletePatronById(Long id) throws PatronServiceException;
    void deleteAllPatron();

}
