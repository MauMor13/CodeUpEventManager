package com.mindhub.event_manager.models;

import com.mindhub.event_manager.enums.CustomerRol;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Admin extends Person{

    public Admin(String lastname, String name, String email, String password, CustomerRol rol) {
        super(lastname, name, email, password, rol);
    }

}
