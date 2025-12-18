package com.example.Security.Services;

import com.example.Security.Models.Person;
import com.example.Security.Repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PersonRepositories personRepositories;

    @Autowired
    public RegistrationService(PersonRepositories personRepositories) {
        this.personRepositories = personRepositories;
    }

    @Transactional
    public void register(Person person){
        personRepositories.save(person);
    }

}

