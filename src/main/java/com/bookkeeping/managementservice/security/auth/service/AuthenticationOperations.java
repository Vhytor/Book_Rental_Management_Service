package com.bookkeeping.managementservice.security.auth.service;

import com.bookkeeping.managementservice.security.auth.model.AuthenticationRequest;
import com.bookkeeping.managementservice.security.auth.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationOperations {

    private final AuthenticationComponent authenticationComponent;

    public AuthenticationResponse authenticationResponse(AuthenticationRequest authenticationRequest){
        return authenticationComponent.authenticationResponse(authenticationRequest);

    }
}
