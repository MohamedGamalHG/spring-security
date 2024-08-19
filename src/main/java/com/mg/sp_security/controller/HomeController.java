package com.mg.sp_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String home(HttpServletRequest httpServletRequest)
    {
        return "hello this the home page" + httpServletRequest.getSession().getId();
    }

}
