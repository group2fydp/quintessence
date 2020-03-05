package com.cove.login.controllers;

import com.cove.login.constants.WebResourceKeyConstants;
import com.cove.login.requestDTO.LoginRequestDTO;
import com.cove.login.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO requestDTO, HttpServletRequest request) {

        String token = loginService.login(requestDTO, request);
        return ok().body(loginService.login(requestDTO, request));
    }

}
