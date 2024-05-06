package com.bookkeeping.managementservice.service;

import com.bookkeeping.managementservice.config.ApplicationConfig;
import com.bookkeeping.managementservice.data.model.Role;
import com.bookkeeping.managementservice.data.model.User;
import com.bookkeeping.managementservice.data.repository.UserRepository;
import com.bookkeeping.managementservice.exception.UserServiceException;
import com.bookkeeping.managementservice.payload.UserRequest;
import com.bookkeeping.managementservice.payload.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Override
    public UserResponse registerUser(UserRequest userRequest) throws UserServiceException {
        validateUserRequest(userRequest);
        return getUserResponse(userRepository.save(User.builder()
                        .email(userRequest.getEmail())
                        .password(applicationConfig.passwordEncoder().encode(userRequest.getPassword()))
                        .isEnabled(true)
                        .role(Role.ADMIN)
                        .build()));
    }

    private void validateUserRequest(UserRequest userRequest) throws UserServiceException {
        String email = userRequest.getEmail();
        if (userRepository.existsByEmail(userRequest.getEmail())){
            throw new UserServiceException(" User with email " + email + "already exists");
        }

    }
    private UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .errors(new ArrayList<>())
                .build();
    }



    @Override
    public UserResponse getUserByEmailCredentials(String email) throws UserServiceException {
        return getUserResponse(getUserByEmail(email));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(" User not found "));
    }

    @Override
    public UserResponse getUserByIdCredentials(Long id) {
        return getUserResponse(getUserById(id));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException(" User not found "));
    }

    @Override
    public UserResponse updatePassword(UserRequest userRequest) {
        return getUserResponse(userRepository.save(User.builder()
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .build()));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }
}
