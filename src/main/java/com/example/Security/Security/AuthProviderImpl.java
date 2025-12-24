//package com.example.Security.Security;
//
//import com.example.Security.Services.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    private final PersonService personService;
//
//    @Autowired
//    public AuthProviderImpl(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        UserDetails personDetails = personService.loadUserByUsername(username);
//
//        String password = authentication.getCredentials().toString();
//
//       if (!password.equals(personDetails.getPassword()))
//           throw new BadCredentialsException("Incorrect password!");
//
//       return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
