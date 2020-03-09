package com.cove.login.services;

import com.cove.login.requestDTO.LoginRequestDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    String login(LoginRequestDTO requestDTO, HttpServletRequest request, String requestHeader);
}
