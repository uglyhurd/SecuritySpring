package com.example.Security.Services;

import com.example.Security.Models.Person;
import com.example.Security.Repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PersonRepositories personRepositories;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(PersonRepositories personRepositories, PasswordEncoder passwordEncoder) {
        this.personRepositories = personRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person){

        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);

        personRepositories.save(person);
    }

}

