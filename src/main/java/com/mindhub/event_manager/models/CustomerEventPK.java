package com.mindhub.event_manager.models;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class CustomerEventPK {

    private UUID customerId;

    private UUID reactionId;

}
