package com.mindhub.event_manager.models;

import com.mindhub.event_manager.enums.ReactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reaction {

    @EmbeddedId
    private ReactionId reactionId;

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @ManyToOne
    @JoinColumn(name= "event_id",insertable = false, updatable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "customer_id",insertable = false, updatable = false)
    private Customer customer;

    public Reaction(Event event, Customer customer, ReactionType reactionType){
        this.reactionType = reactionType;
        this.event = event;
        this.customer = customer;
        reactionId = new ReactionId(event.getEvent_id(),customer.getCustomer_id());
    }

}
