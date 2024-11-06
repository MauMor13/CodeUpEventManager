package com.mindhub.event_manager.controllers;

import com.mindhub.event_manager.DTOs.PatchCustomerDTO;
import com.mindhub.event_manager.DTOs.PersonLoginDTO;
import com.mindhub.event_manager.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get_customer_auth")
    public ResponseEntity<?> getCustomerAuthenticated (){
        return customerService.getCustomerAuthenticated();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer (PersonLoginDTO newCustomer){
        return customerService.registerCustomer(newCustomer);
    }

    @PatchMapping("/attribute_modification")
    public ResponseEntity<?> patchAttributeCustomer (@Valid @RequestBody PatchCustomerDTO patchCustomerDTO){
        return customerService.patchAttributeCustomer(patchCustomerDTO);
    }
}
