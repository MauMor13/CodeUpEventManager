package com.mindhub.event_manager.models;

import com.mindhub.event_manager.enums.ReactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reaction implements Serializable {

    /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID reaction_id;*/
    @EmbeddedId
    private CustomerEventPK reaction_id;

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @ManyToOne
    @JoinColumn(name ="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name ="event_id")
    @MapsId("")
    private Event event;

    public Reaction(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

}
