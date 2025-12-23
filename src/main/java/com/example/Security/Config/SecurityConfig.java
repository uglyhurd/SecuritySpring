package com.example.Security.Config;


import com.example.Security.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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


    // Настроиваем аунтификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(lalal)
        auth.userDetailsService(personService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}






















