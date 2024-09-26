package com.mindhub.event_manager.DTOs;

import com.mindhub.event_manager.models.EventLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class EventLocationDTO {

    private UUID id;

    private LocalDateTime date;

    private int assistance;

    private LocationDTO location;

    public EventLocationDTO(EventLocation eventLocation) {
        this.id = eventLocation.getEventLocation_id();
        this.date = eventLocation.getDate();
        this.assistance = eventLocation.getAssistance();
        this.location = new LocationDTO(eventLocation.getLocation());
    }

}
