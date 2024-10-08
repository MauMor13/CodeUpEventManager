package com.mindhub.event_manager.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonLoginDTO {

    private String password;

    private String email;

    public PersonLoginDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
