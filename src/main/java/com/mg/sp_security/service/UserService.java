package com.mg.sp_security.service;

import com.mg.sp_security.model.Users;
import com.mg.sp_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService service;
    @Autowired
    private AuthenticationManager manager;
    public Users register(Users users)
    {
        return userRepository.save(users);
    }

    public String verify(Users users) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));

        if(authentication.isAuthenticated())
            return service.generateToke(users.getUsername());
        return "fail";
    }
}
