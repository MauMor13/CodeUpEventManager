package com.mindhub.event_manager.services.impl;

import com.mindhub.event_manager.DTOs.CustomerDTO;
import com.mindhub.event_manager.DTOs.PatchCustomerDTO;
import com.mindhub.event_manager.DTOs.PersonLoginDTO;
import com.mindhub.event_manager.models.Customer;
import com.mindhub.event_manager.repositories.CustomerRepository;
import com.mindhub.event_manager.services.CustomerService;
import com.mindhub.event_manager.services.sendEmail.ServiceSendEmail;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ServiceSendEmail serviceSendEmail;

    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ServiceSendEmail serviceSendEmail,
                               PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.serviceSendEmail = serviceSendEmail;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public boolean emailIsValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

    @Override
    public ResponseEntity<?> deleteCustomer(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty())
            return new ResponseEntity<>("Were not found customer", HttpStatus.NOT_FOUND);

        customer.get().setActivated(false);
        customerRepository.save(customer.get());
        return new ResponseEntity<>("Delete customer", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCustomerAuthenticated() {

        Authentication authenticationCustomer = SecurityContextHolder.getContext().getAuthentication();

        if (authenticationCustomer == null){
            return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
        }

        Customer customerAuth = this.findByEmail(authenticationCustomer.getName());
        CustomerDTO customerDTO = new CustomerDTO(customerAuth);

        return new ResponseEntity<>(customerDTO ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerCustomer(PersonLoginDTO newCustomer) {

        if (!this.emailIsValid(newCustomer.getEmail())){
            return new ResponseEntity<>("Your email is invalid", HttpStatus.BAD_REQUEST);
        }
        if(customerRepository.existsByEmail(newCustomer.getEmail())){
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }

        Customer customer = new Customer(
                passwordEncoder.encode(newCustomer.getPassword()),
                newCustomer.getEmail()
        );
        customerRepository.save(customer);

        try{
            serviceSendEmail.sendCode(customer.getEmail());
        }catch (MessagingException | UnsupportedEncodingException e){
            return new ResponseEntity<>("The confirmation email could not be issued, please try again",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("\n" + "Correctly registered, please check the email to confirm",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patchAttributeCustomer(PatchCustomerDTO patchCustomerDTO) {

        Authentication authenticationCustomer = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationCustomer == null){
            return new ResponseEntity<>("Customer not found", HttpStatus.BAD_REQUEST);
        }
        Customer customerAuth = this.findByEmail(authenticationCustomer.getName());

        customerAuth.setAge(patchCustomerDTO.age());
        customerAuth.setName(patchCustomerDTO.name());
        customerAuth.setLastname(patchCustomerDTO.lastname());
        customerAuth.setGender(patchCustomerDTO.gender());

        return  new ResponseEntity<>("Customer modification ok", HttpStatus.OK);
    }

}
