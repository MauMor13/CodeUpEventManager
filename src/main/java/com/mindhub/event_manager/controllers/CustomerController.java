package com.mindhub.event_manager.controllers;

import com.mindhub.event_manager.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get_customers")
    public ResponseEntity<?> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/get_customer")
    public ResponseEntity<?> getCustomer(@RequestParam UUID id){
        return customerService.getCustomer(id);
    }

}
