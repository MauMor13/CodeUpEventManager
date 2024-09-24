package com.mindhub.event_manager.repositories;

import com.mindhub.event_manager.models.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
