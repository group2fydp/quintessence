package com.cove.user.controllers;

import com.cove.user.dto.model.UserRequestDTO;
import com.cove.user.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok().body(authenticationService.searchUser(userRequestDTO));
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok().body(authenticationService.updateUser(userRequestDTO));
    }

    //This is just for authenticating the user, different from getStudent or clinicianByUsername functionality
    @GetMapping(value = "/fetch/{username}")
    public ResponseEntity<?> fetchUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(authenticationService.fetchUserByUsername(username));
    }
}
