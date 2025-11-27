package com.example.Security.Repositories;

import com.example.Security.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepositories extends JpaRepository<Person, Integer> {

 Optional<Person> findByUsername(String username);

}
