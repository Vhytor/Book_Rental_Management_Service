package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.data.model.Gender;
import com.bookkeeping.managementservice.data.model.Patron;
import com.bookkeeping.managementservice.data.repository.PatronRepository;
import com.bookkeeping.managementservice.exception.PatronServiceException;
import com.bookkeeping.managementservice.payload.PatronRequest;
import com.bookkeeping.managementservice.payload.PatronResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional
    public PatronResponse registerPatron(PatronRequest patronRequest) throws PatronServiceException {
        Optional<PatronResponse> patronResponse = validatePatronRequest(patronRequest);
        return patronResponse.orElseGet(()-> getPatronResponse(patronRepository.save(Patron.builder()
                .firstname(patronRequest.getFirstname())
                .lastname(patronRequest.getLastname())
                .email(patronRequest.getEmail())
                .phone_number(patronRequest.getPhone_number())
                .gender(Gender.valueOf(patronRequest.getGender()))
                .build())));
    }
    private PatronResponse getPatronResponse(Patron patron){
        return PatronResponse.builder()
                .id(patron.getId())
                .firstname(patron.getFirstname())
                .lastname(patron.getLastname())
                .email(patron.getEmail())
                .gender(String.valueOf(patron.getGender()))
                .phoneNumber(patron.getPhone_number())
                .build();
    }

    private Optional<PatronResponse> validatePatronRequest(PatronRequest patronRequest) {
        String email = patronRequest.getEmail();
        String phone_number= patronRequest.getPhone_number();

        if (patronRepository.existsByEmail(email)){
            return Optional.of(PatronResponse.builder()
                    .message(" Patron with email " + email + " already exists")
                    .build());
        }
        if (patronRepository.existsByPhone_number(phone_number)){
            return Optional.of(PatronResponse.builder()
                    .message(" Patron with phone_number " + phone_number + " already exists ")
                    .build());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public PatronResponse updatePatron(Long patronId, PatronRequest patronRequest) throws PatronServiceException {
        Patron existingPatron = getPatron(patronId);

        if (patronRequest.getFirstname() != null){
            existingPatron.setFirstname(patronRequest.getFirstname());
        }
        if (patronRequest.getLastname() != null){
            existingPatron.setLastname(patronRequest.getLastname());
        }
        if (patronRequest.getEmail() != null && !patronRepository.existsByEmail(patronRequest.getEmail())){
            existingPatron.setEmail(patronRequest.getEmail());
        }
        if (patronRequest.getGender() != null){
            existingPatron.setGender(Gender.valueOf(patronRequest.getGender()));
        }
        if (patronRequest.getPhone_number() != null && !patronRepository.existsByPhone_number(patronRequest.getPhone_number())){
            existingPatron.setPhone_number(patronRequest.getPhone_number());
        }
        patronRepository.save(existingPatron);

        return getPatronResponse(existingPatron);
    }

    private Patron getPatron(Long patronId) throws PatronServiceException {
        return patronRepository.findById(patronId).orElseThrow(()-> new PatronServiceException(" Patron with id " + patronId + "does not exist"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatronResponse> getAllPatron() {
        List<Patron> patrons = patronRepository.findAll();
        return patrons.stream().map(this::getPatronResponse).toList();
    }

    @Override
    public PatronResponse getPatronById(Long patronId) {
        return null;
    }

    @Override
    public void deletePatronById(Long patronId) throws PatronServiceException {

    }

    @Override
    public void deleteAllPatron() {

    }
}
