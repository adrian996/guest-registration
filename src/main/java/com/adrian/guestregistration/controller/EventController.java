package com.adrian.guestregistration.controller;

import com.adrian.guestregistration.model.Event;
import com.adrian.guestregistration.model.EventParticipant;
import com.adrian.guestregistration.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/events")
@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        log.info("Getting all events");
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        log.info("Getting event by id " + id);
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<EventParticipant>> getParticipantsByEventId(@PathVariable Long id) {
        log.info("Getting event participants by id " + id);
        List<EventParticipant> participants = eventService.getParticipantsByEventId(id);
        return ResponseEntity.status(HttpStatus.OK).body(participants);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        log.info("Creating new event: {}", event);
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.OK).body(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        log.info("Updating event with id " + id);
        Event updated = eventService.updateEvent(id, updatedEvent);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.info("Deleting event with id " + id);
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/future")
    public ResponseEntity<List<Event>> getFutureEvents() {
        log.info("Getting future events");
        List<Event> futureEvents = eventService.getFutureEvents();
        return ResponseEntity.ok(futureEvents);
    }

    @GetMapping("/past")
    public ResponseEntity<List<Event>> getPastEvents() {
        log.info("Getting past events");
        List<Event> pastEvents = eventService.getPastEvents();
        return ResponseEntity.ok(pastEvents);
    }
}

