package com.bookkeeping.managementservice.security.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface JwtAuthenticationManager {
    void authenticate(@NonNull HttpServletRequest httpServletRequest,
                      @NonNull HttpServletResponse httpServletResponse,
                      @NonNull FilterChain filterChain) throws ServletException, IOException;
}
