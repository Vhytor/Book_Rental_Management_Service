package com.bookkeeping.managementservice.controller;

import com.bookkeeping.managementservice.exception.UserServiceException;
import com.bookkeeping.managementservice.payload.UserRequest;
import com.bookkeeping.managementservice.payload.UserResponse;
import com.bookkeeping.managementservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) throws UserServiceException {
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUserByEmailCredentials(@PathVariable  String email) throws UserServiceException {
        return ResponseEntity.ok(userService.getUserByEmailCredentials(email));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserByIdCredentials(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserByIdCredentials(id));
    }
    @PutMapping("/")
    public ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.updatePassword(userRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
