package com.mindhub.event_manager.models;

import com.mindhub.event_manager.enums.CustomerGender;
import com.mindhub.event_manager.enums.CustomerRol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer extends Person {


    private byte age;

    @Enumerated(EnumType.STRING)
    private CustomerGender gender;

    @OneToMany (mappedBy = "customer")
    private Set<Event> events = new HashSet<>();

    @OneToMany (mappedBy = "customer")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<CustomerEventLocation> customerEventLocations = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Reaction> reactions = new HashSet<>();

    public Customer(String name, String lastname, String email, String password, byte age, CustomerGender gender, CustomerRol rol) {
        super(lastname, name, email, password, rol);
        this.age = age;
        this.gender = gender;
    }
    public Customer(String email,String password){
        super(email, password);
    }

    public void addEvent(Event event){
        event.setCustomer(this);
        this.events.add(event);
    }

    public void addComment(Comment comment){
        comment.setCustomer(this);
        this.comments.add(comment);
    }

    public void addCustomerEvent(CustomerEventLocation customerEventLocation){
        customerEventLocation.setCustomer(this);
        this.customerEventLocations.add(customerEventLocation);
    }

    public void addReaction(Reaction reaction){
        reaction.setCustomer(this);
        this.reactions.add(reaction);
    }

}
