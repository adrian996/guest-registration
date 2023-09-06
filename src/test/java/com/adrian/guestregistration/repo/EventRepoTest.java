package com.adrian.guestregistration.repo;

import com.adrian.guestregistration.model.Event;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EventRepoTest {

    @Autowired
    private EventRepo eventRepo;

    @Test
    public void testFindByDateAfter() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Event event1 = new Event(1L, "Event 1", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");
        Event event2 = new Event(2L, "Event 2", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");
        Event event3 = new Event(3L, "Event 3", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");

        eventRepo.save(event1);
        eventRepo.save(event2);
        eventRepo.save(event3);

        List<Event> eventsAfterNow = eventRepo.findByDateAfter(now);

        assertEquals(3, eventsAfterNow.size());
        assertEquals("Event 1", eventsAfterNow.get(0).getName());
    }

    @Test
    public void testFindByDateBefore() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().plusDays(2));
        Event event1 = new Event(1L, "Event 1", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");
        Event event2 = new Event(2L, "Event 2", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");
        Event event3 = new Event(3L, "Event 3", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");

        eventRepo.save(event1);
        eventRepo.save(event2);
        eventRepo.save(event3);

        List<Event> eventsBeforeNow = eventRepo.findByDateBefore(now);

        assertEquals(3, eventsBeforeNow.size());
        assertEquals("Event 1", eventsBeforeNow.get(0).getName());
        assertEquals("Event 2", eventsBeforeNow.get(1).getName());
    }
}