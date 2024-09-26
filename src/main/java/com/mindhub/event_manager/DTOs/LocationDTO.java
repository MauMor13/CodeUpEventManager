package com.mindhub.event_manager.DTOs;

import com.mindhub.event_manager.models.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class LocationDTO {

    private UUID id;

    private String name;

    private int capacity;

    private String img;

    public LocationDTO(Location location) {
        this.id = location.getLocation_id();
        this.name = location.getName();
        this.capacity = location.getCapacity();
        this.img = location.getImg();
    }

}
