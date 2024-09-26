package com.mindhub.event_manager;

import com.mindhub.event_manager.enums.CustomerGender;
import com.mindhub.event_manager.enums.CustomerRol;
import com.mindhub.event_manager.enums.ReactionType;
import com.mindhub.event_manager.models.*;
import com.mindhub.event_manager.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataBaseH2Initializer {

    @Bean
    public CommandLineRunner initData(CustomerRepository customerRepository,
                                      EventRepository eventRepository,
                                      CommentRepository commentRepository,
                                      EventLocationRepository eventLocationRepository,
                                      LocationRepository locationRepository,
                                      CustomerEventLocationRepository customerEventLocationRepository,
                                      ReactionRepository reactionRepository) {
        return args -> {

            Customer customer1 = new Customer("Mauricio", "Mores","mauri.f.mores@gmail.com","mauri1313", (byte) 27, CustomerGender.MALE, CustomerRol.USER);
            Event event1 = new Event("Red Bull Grand Prix of The Americas", (byte) 18, "The Circuit Of The Americas is the first purpose-built Grand Prix facility in the U.S. near Austin, Texas, with a 5.513km track and a capacity for 120,000 fans. Construction of the impressive 1000-acre facility was completed late in 2012, with MotoGP lining up at the track for the first time in 2013.",null);
            Comment comment1 = new Comment("Great event with a track full of emotions");
            Comment comment2 = new Comment("The event was very good, with great shows");
            Location location1 = new Location("Austin, Texas, USA", 40000, null);
            EventLocation eventLocation1 = new EventLocation(LocalDateTime.of(2025,12,4,10,30), 40000);
            CustomerEventLocation customerEventLocation1 = new CustomerEventLocation();

            customer1.addCustomerEvent(customerEventLocation1);
            customer1.addComment(comment1);
            customer1.addComment(comment2);
            customer1.addEvent(event1);

            eventLocation1.addCustomerEvent(customerEventLocation1);

            event1.addEventLocation(eventLocation1);
            event1.addComment(comment1);
            event1.addComment(comment2);

            location1.addEventLocation(eventLocation1);

            customerRepository.save(customer1);
            eventRepository.save(event1);
            commentRepository.save(comment1);
            commentRepository.save(comment2);
            locationRepository.save(location1);
            eventLocationRepository.save(eventLocation1);
            customerEventLocationRepository.save(customerEventLocation1);

            Reaction reaction1 = new Reaction(event1, customer1, ReactionType.LIKE);
            customer1.addReaction(reaction1);
            event1.addReaction(reaction1);
            Reaction reaction2 = new Reaction(event1, customer1, ReactionType.DISLIKE);
            customer1.addReaction(reaction2);
            event1.addReaction(reaction2);
            reactionRepository.save(reaction1);
            reactionRepository.save(reaction2);
        };
    }
}
