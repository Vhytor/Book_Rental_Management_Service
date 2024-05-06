package com.bookkeeping.managementservice.controller;

import com.bookkeeping.managementservice.exception.PatronServiceException;
import com.bookkeeping.managementservice.payload.PatronRequest;
import com.bookkeeping.managementservice.payload.PatronResponse;
import com.bookkeeping.managementservice.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @PostMapping
    public ResponseEntity<PatronResponse> registerPatron(@RequestBody PatronRequest patronRequest) throws PatronServiceException {
        PatronResponse patronResponse = patronService.registerPatron(patronRequest);
        return new ResponseEntity<>(patronResponse, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatronResponse> updatePatron(@PathVariable Long id,@RequestBody PatronRequest patronRequest) throws PatronServiceException {
        PatronResponse patronResponse = patronService.updatePatron(id,patronRequest);
        return new ResponseEntity<>(patronResponse,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PatronResponse>> getAllPatrons(){
        List<PatronResponse> patronResponses = patronService.getAllPatron();
        return new ResponseEntity<>(patronResponses,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatronResponse> getPatronById(@PathVariable Long id) throws PatronServiceException {
        PatronResponse patronResponse = patronService.getPatronById(id);
        return ResponseEntity.ok().body(patronResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatron(@PathVariable Long id) throws PatronServiceException {
        patronService.deletePatronById(id);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<?> deleteAllPatron(){
        patronService.deleteAllPatron();
        return ResponseEntity.ok().build();
    }



}
