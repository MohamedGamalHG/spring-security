package com.mg.sp_security.controller;


import com.mg.sp_security.model.Users;
import com.mg.sp_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public Users register(@RequestBody Users users)
    {
        users.setPassword(encoder.encode(users.getPassword()));
        return userService.register(users);
    }

    @PostMapping("/login")
    public String verify(@RequestBody Users users)
    {
        return userService.verify(users);
    }

}
