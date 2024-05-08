package com.bookkeeping.managementservice.security.filter;

import com.bookkeeping.managementservice.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@AllArgsConstructor
public class JwtAuthenticationProvider implements JwtAuthenticationManager {

    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    @Override
    public void authenticate(@NonNull HttpServletRequest httpServletRequest,
                             @NonNull HttpServletResponse httpServletResponse,
                             @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String jwt;
        final String userEmail;
        final String authenticationHeader = getHeaderValue(httpServletRequest, "Authorization");

        if (isNullAuthenticationHeader(authenticationHeader) || !isAuthenticationHeaderStartsWithBearer(authenticationHeader)) {
            continueFilterChain(httpServletRequest, httpServletResponse, filterChain);
            return;

        }
        jwt = extractJwtToken(authenticationHeader);
        userEmail = jwtService.getUsername(jwt);

        if (isValidUserEmail(userEmail) && isEmptySecurityContext()) {
            UserDetails userDetails = getUserDetails(userEmail);
            setSecurityContextIfTokenValid(httpServletRequest,userDetails,jwt);
        }
        continueFilterChain(httpServletRequest,httpServletResponse,filterChain);

    }


    private String getHeaderValue(HttpServletRequest httpServletRequest,String headerKey) {
        return httpServletRequest.getHeader(headerKey);
    }
    private boolean isNullAuthenticationHeader(String authenticationHeader){
        return authenticationHeader == null;
    }
    private boolean isAuthenticationHeaderStartsWithBearer(String authenticationHeader) {
        return authenticationHeader.startsWith(" Bearer");
    }
    private void continueFilterChain(HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse,
                                     FilterChain filterChain) throws ServletException, IOException{
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
    private String extractJwtToken(String authenticationHeader) {
        return authenticationHeader.substring(7);
    }
    private boolean isValidUserEmail(String userEmail) {
        return userEmail != null;
    }
    private boolean isEmptySecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }
    private UserDetails getUserDetails(String userEmail) {
        return userDetailsService.loadUserByUsername(userEmail);
    }
    private void setSecurityContextIfTokenValid(HttpServletRequest httpServletRequest,
                                                UserDetails userDetails,
                                                String jwt) {
        if (jwtService.isTokenValid(jwt,userDetails)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
    }

}
