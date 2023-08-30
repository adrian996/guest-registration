package com.adrian.guestregistration.service;

import com.adrian.guestregistration.model.Event;
import com.adrian.guestregistration.model.EventParticipant;
import com.adrian.guestregistration.repo.EventRepo;
import com.adrian.guestregistration.validator.EntityValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;
    private final EntityValidator entityValidator;

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepo.findById(id);
    }

    @Transactional
    public Event createEvent(Event event) {
        //entityValidator.validateEvent(event);
        return eventRepo.save(event);
    }

    @Transactional
    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = eventRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        //entityValidator.validateEvent(updatedEvent);

        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setVenue(updatedEvent.getVenue());
        existingEvent.setAdditionalInformation(updatedEvent.getAdditionalInformation());

        return eventRepo.save(existingEvent);
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }
}
