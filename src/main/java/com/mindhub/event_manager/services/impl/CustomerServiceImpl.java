package com.mindhub.event_manager.services.impl;

import com.mindhub.event_manager.DTOs.CustomerDTO;
import com.mindhub.event_manager.models.Customer;
import com.mindhub.event_manager.repositories.CustomerRepository;
import com.mindhub.event_manager.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<?> getCustomers() {

        List<CustomerDTO> customers = customerRepository.findAll().stream().map(CustomerDTO::new).toList();

        if (customers.isEmpty())
            return new ResponseEntity<>("Were not found customers", HttpStatus.NOT_FOUND);

        return  new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCustomer(UUID id) {
        Optional<Customer> customerDTO = customerRepository.findById(id);
        if (customerDTO.isEmpty())
            return new ResponseEntity<>("Were not found customer", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new CustomerDTO(customerDTO.get()), HttpStatus.OK);
    }

}
