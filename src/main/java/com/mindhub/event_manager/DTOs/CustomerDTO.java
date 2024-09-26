package com.mindhub.event_manager.DTOs;

import com.mindhub.event_manager.enums.CustomerGender;
import com.mindhub.event_manager.enums.CustomerRol;
import com.mindhub.event_manager.models.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class CustomerDTO {

    private UUID id;

    private String name;

    private String lastname;

    private String email;

    private byte age;

    private CustomerGender gender;

    private CustomerRol rol;

    private List<EventDTO> events;

    private List<CommentDTO> comments ;

    private List<ReactionDTO> reactions ;

    public CustomerDTO(Customer customer) {
        this.id = customer.getCustomer_id();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.lastname = customer.getLastname();
        this.age = customer.getAge();
        this.gender = customer.getGender();
        this.rol = customer.getRol();
        this.events = customer.getEvents().stream().map(EventDTO::new).toList();
        this.comments = customer.getComments().stream().map(CommentDTO::new).toList();
        this.reactions = customer.getReactions().stream().map(ReactionDTO::new).toList();
    }
}
