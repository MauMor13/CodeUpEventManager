package com.mindhub.event_manager.controllers;

import com.mindhub.event_manager.DTOs.PersonLoginDTO;
import com.mindhub.event_manager.configurations.securityServices.AuthenticationService;
import com.mindhub.event_manager.configurations.securityServices.JwtService;
import com.mindhub.event_manager.models.LoginResponse;
import com.mindhub.event_manager.models.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody PersonLoginDTO personLoginDTO) {

        Person authenticatedPerson = authenticationService.authenticate(personLoginDTO);

        String jwtToken = jwtService.generateToken(authenticatedPerson);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
