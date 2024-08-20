package com.mg.sp_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Customizer is part of security config package

//        Customizer<CsrfConfigurer<HttpSecurity>> customizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };

       return http.csrf(c -> c.disable()) // tell spring that disable csrf token
                .authorizeHttpRequests(a -> a.anyRequest().authenticated())// tell spring that any request will be authorized
                // this tell spring to put the form login again to put username & pass and take care about Customizer.withDefaults()
                // if i use this with postman it raises code html of login from
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()) // this check if the call come from postman don't get html but return the data clear
                // this tell spring that we will not use the session and this will work fine with postman but will not work fine with browser if i want this to work
                // with browser it should pause the .formLogin(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        // i do that because i make bcrypt password when register user and use strength 12 in usercontroller
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
//    @Bean
    /*
     to verify username & password we can use UserDetailsService by default spring is use it but if you want to
     customize it you should make it as Bean so in code below we override the default behavior that spring use it
     and make the customization that we want in below we tell spring that we use InMemoryUserDetailsManger(take UserDetails)
     this make users in memory along the application is running and use User that from spring security
     and put the username & password & withDefaultPasswordEncoder tell spring that it will check on password as plain text
     not hast password
    *
    * */
//    public UserDetailsService userDetailsService(){
//
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .password("23")
//                .username("mohamed")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }
}
