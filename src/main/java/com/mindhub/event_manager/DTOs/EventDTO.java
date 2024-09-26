package com.mindhub.event_manager.DTOs;

import com.mindhub.event_manager.models.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class EventDTO {

    private UUID id;

    private String name;

    private byte age_req;

    private String desc;

    private String img;

    private List<EventLocationDTO> eventLocations;

    public EventDTO(Event event) {
        this.id = event.getEvent_id();
        this.name = event.getName();
        this.age_req = event.getAge_req();
        this.desc = event.getDesc();
        this.img = event.getImg();
        this.eventLocations = event.getEventLocations().stream().map(EventLocationDTO::new).toList();
    }
}
