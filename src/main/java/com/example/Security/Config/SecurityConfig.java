package com.example.Security.Config;


import com.example.Security.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.ExecutionException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PersonService personService;

    @Autowired
    public SecurityConfig(PersonService personService) {
        this.personService = personService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/auth/login", "/auth/registration" ,"/error").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                formLogin -> formLogin.loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
        ).logout(log -> log.logoutUrl("/logout").logoutSuccessUrl("/auth/login")
                )
        ;
        return http.build();

    }

    //    private final AuthProviderImpl authProvider;
//
//    @Autowired
//    public SecurityConfig(AuthProviderImpl authProvider) {
//        this.authProvider = authProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//    }


    // Настраиваем аунтификацию

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(personService).passwordEncoder(getPasswordEncoder());
        return authenticationManagerBuilder.build();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}






















