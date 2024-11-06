package com.mindhub.event_manager.DTOs;

import com.mindhub.event_manager.enums.CustomerGender;
import jakarta.validation.constraints.NotNull;

public record PatchCustomerDTO(
        @NotNull(message = "required name") String name,
        @NotNull(message = "required lastname") String lastname,
        @NotNull(message = "required age") byte age,
        @NotNull(message = "required gender") CustomerGender gender) {
}
