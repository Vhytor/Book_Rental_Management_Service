package com.bookkeeping.managementservice.security.service;

import com.bookkeeping.managementservice.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private JwtOperations jwtOperations;

    @Autowired
    public JwtService(JwtOperations jwtServiceOperations){
        this.jwtOperations = jwtServiceOperations;
    }
    public String getUsername(String jwt){
        return jwtOperations.extractUsername(jwt);
    }

    public String getToken(UserDetails userDetails) {
        return jwtOperations.generateToken(userDetails);
    }

    public boolean isTokenValid(String token,UserDetails userDetails) {
        return jwtOperations.isTokenValid(token,userDetails);
    }
}
