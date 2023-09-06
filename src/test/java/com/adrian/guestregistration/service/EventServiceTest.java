package com.adrian.guestregistration.service;

import com.adrian.guestregistration.dto.ParticipantRequestDTO;
import com.adrian.guestregistration.enums.ParticipantType;
import com.adrian.guestregistration.model.*;
import com.adrian.guestregistration.repo.EventRepo;
import com.adrian.guestregistration.validator.EntityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepo eventRepo;

    @Mock
    private PersonService personService;

    @Mock
    private CompanyService companyService;

    @Mock
    private EntityValidator entityValidator;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllEvents() {
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        events.add(event);

        when(eventRepo.findAll()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();
        assertEquals(1, result.size());
    }

    @Test
    void shouldGetEventById() {
        Event event = new Event();
        event.setId(1L);

        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));

        Optional<Event> result = eventService.getEventById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void deleteParticipantFromEvent() {
        Event event = new Event();
        event.setId(1L);
        ParticipantRequestDTO participantRequestDTO = new ParticipantRequestDTO();
        participantRequestDTO.setEventId(1L);
        participantRequestDTO.setParticipantType(ParticipantType.PERSON);
        participantRequestDTO.setParticipantId(2L);

        Person person = new Person();
        person.setId(2L);

        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));
        when(personService.getPersonById(2L)).thenReturn(Optional.of(person));

        event.getPersons().add(person);

        eventService.deleteParticipantFromEvent(participantRequestDTO);

        assertFalse(event.getPersons().contains(person));
    }


}
