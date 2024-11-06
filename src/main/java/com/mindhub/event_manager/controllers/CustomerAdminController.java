package com.mindhub.event_manager.controllers;

import com.mindhub.event_manager.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class CustomerAdminController {

    private final CustomerService customerService;

    public CustomerAdminController(CustomerService customerService) {
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

    @DeleteMapping("/delete_customer")
    public ResponseEntity<?> deleteCustomer(@RequestParam UUID id){
        return customerService.deleteCustomer(id);
    }

}
