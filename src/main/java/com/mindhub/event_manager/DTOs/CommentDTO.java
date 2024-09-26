package com.mindhub.event_manager.DTOs;

import com.mindhub.event_manager.models.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class CommentDTO {

    private UUID id;

    private String comment;

    public CommentDTO(Comment comment) {
        this.id = comment.getComment_id();
        this.comment = comment.getComment();
    }
}
