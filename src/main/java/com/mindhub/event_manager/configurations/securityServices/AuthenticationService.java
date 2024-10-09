package com.mindhub.event_manager.configurations.securityServices;

import com.mindhub.event_manager.DTOs.PersonLoginDTO;
import com.mindhub.event_manager.models.Person;
import com.mindhub.event_manager.services.PersonService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class AuthenticationService {

    private final PersonService personService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            PersonService personService,
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
    }

    public Person authenticate(PersonLoginDTO personLoginDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        personLoginDTO.getEmail(),
                        personLoginDTO.getPassword()
                )
        );

        Person person = personService.findByEmail(personLoginDTO.getEmail()).orElseThrow();
        String rol = person.getAuthorities().toString();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                personLoginDTO.getEmail(),
                personLoginDTO.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(rol))
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        return person;
    }

}
