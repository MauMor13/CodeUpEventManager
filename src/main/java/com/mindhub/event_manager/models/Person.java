package com.mindhub.event_manager.models;

import com.mindhub.event_manager.enums.CustomerRol;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID customer_id;

    private String name;

    private String lastname;

    private String email;

    private boolean activated = true;

    private String password;

    @Enumerated(EnumType.STRING)
    private CustomerRol rol;

    public Person(String lastname, String name, String email, String password, CustomerRol rol) {
        this.lastname = lastname;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

}