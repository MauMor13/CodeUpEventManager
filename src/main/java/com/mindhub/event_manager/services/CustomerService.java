package com.mindhub.event_manager.services;

import com.mindhub.event_manager.DTOs.PatchCustomerDTO;
import com.mindhub.event_manager.DTOs.PersonLoginDTO;
import com.mindhub.event_manager.models.Customer;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

public interface CustomerService {

    Customer findByEmail(String email);

    ResponseEntity<?> getCustomers();

    ResponseEntity<?> getCustomer(UUID id);

    ResponseEntity<?> deleteCustomer(UUID id);

    ResponseEntity<?> getCustomerAuthenticated();

    ResponseEntity<?> registerCustomer(PersonLoginDTO newCustomer);

    ResponseEntity<?> patchAttributeCustomer(PatchCustomerDTO patchCustomerDTO);
}
