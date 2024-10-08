package com.mindhub.event_manager.services.impl;

import com.mindhub.event_manager.models.Person;
import com.mindhub.event_manager.repositories.PersonRepository;
import com.mindhub.event_manager.services.PersonService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }
}
