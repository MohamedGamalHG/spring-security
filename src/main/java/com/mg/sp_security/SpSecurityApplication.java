package com.mg.sp_security;

import com.mg.sp_security.model.Users;
import com.mg.sp_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpSecurityApplication.class, args);
	}


	@Autowired
	private UserRepository userRepository;
	@Bean
	public CommandLineRunner commandLineRunner()
	{
		return args -> {
			Users users = new Users();
			users.setUsername("mohamed");
			users.setPassword("123");

			userRepository.save(users);

		};
	}

}
