package com.mindhub.event_manager.repositories;

import com.mindhub.event_manager.models.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EventLocationRepository extends JpaRepository<EventLocation, UUID> {
}
