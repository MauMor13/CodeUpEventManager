package com.mindhub.event_manager.services;

import com.mindhub.event_manager.models.Person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> findByEmail(String email);

    void save(Person person);

    boolean existsByEmail(String email);

}
