package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.exception.UserServiceException;
import com.bookkeeping.managementservice.payload.UserRequest;
import com.bookkeeping.managementservice.payload.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest) throws UserServiceException;
    UserResponse getUserByEmailCredentials(String email) throws UserServiceException;
    UserResponse getUserByIdCredentials(Long id);
    UserResponse updatePassword(UserRequest userRequest);
    void deleteUser(Long id);
}
