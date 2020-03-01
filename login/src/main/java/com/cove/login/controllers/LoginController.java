package com.cove.login.controllers;

import com.cove.login.constants.WebResourceKeyConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = WebResourceKeyConstants.BASE_API)
public class LoginController {
    @GetMapping("/test")
    public String test() {
        return "test done";
    }
}
