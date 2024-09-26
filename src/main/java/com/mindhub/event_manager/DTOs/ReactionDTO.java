package com.mindhub.event_manager.DTOs;

import com.mindhub.event_manager.enums.ReactionType;
import com.mindhub.event_manager.models.Reaction;
import com.mindhub.event_manager.models.ReactionId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReactionDTO {

    private ReactionId id;

    private ReactionType reactionType;

    public ReactionDTO(Reaction reaction) {
        this.id = reaction.getReactionId();
        this.reactionType = reaction.getReactionType();
    }
}
