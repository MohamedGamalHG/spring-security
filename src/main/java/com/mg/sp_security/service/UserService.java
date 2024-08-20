package com.mg.sp_security.service;

import com.mg.sp_security.model.Users;
import com.mg.sp_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users register(Users users)
    {
        return userRepository.save(users);
    }
}
