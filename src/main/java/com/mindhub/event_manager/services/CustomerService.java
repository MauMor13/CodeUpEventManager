package com.mindhub.event_manager.services;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CustomerService {

    ResponseEntity<?> getCustomers();

    ResponseEntity<?> getCustomer(UUID id);
}
