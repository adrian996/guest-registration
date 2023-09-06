package com.adrian.guestregistration.controller;

import com.adrian.guestregistration.dto.ParticipantRequestDTO;
import com.adrian.guestregistration.model.Company;
import com.adrian.guestregistration.model.Event;
import com.adrian.guestregistration.model.EventParticipant;
import com.adrian.guestregistration.model.Person;
import com.adrian.guestregistration.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EventControllerTest {

    @Mock
    private EventService eventService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        EventController eventController = new EventController(eventService);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldGetAllEvents() throws Exception {
        List<Event> events = new ArrayList<>();
        when(eventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetEventById() throws Exception {
        Long eventId = 1L;
        Event event = new Event();
        event.setId(eventId);
        when(eventService.getEventById(eventId)).thenReturn(Optional.of(event));

        mockMvc.perform(get("/api/events/{id}", eventId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(eventId));
    }

    @Test
    void shouldGetParticipantsByEventId() throws Exception {
        Long eventId = 1L;
        List<EventParticipant> participants = new ArrayList<>();
        when(eventService.getParticipantsByEventId(eventId)).thenReturn(participants);

        mockMvc.perform(get("/api/events/{id}/participants", eventId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldCreateEvent() throws Exception {
        Event event = new Event(1L, "Event 1", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");
        when(eventService.createEvent(any(Event.class))).thenReturn(event);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdateEvent() throws Exception {
        Long eventId = 1L;
        Event updatedEvent = new Event(1L, "Event 1", Timestamp.valueOf(LocalDateTime.now().plusDays(1)), "Venue", "info");

        updatedEvent.setId(eventId);
        when(eventService.updateEvent(eq(eventId), any(Event.class))).thenReturn(updatedEvent);

        mockMvc.perform(put("/api/events/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEvent)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(eventId));
    }

    @Test
    void shouldDeleteEvent() throws Exception {
        Long eventId = 1L;
        doNothing().when(eventService).deleteEvent(eventId);

        mockMvc.perform(delete("/api/events/{id}", eventId))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteParticipantFromEvent() throws Exception {
        ParticipantRequestDTO participantRequestDTO = new ParticipantRequestDTO();
        mockMvc.perform(delete("/api/events/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(participantRequestDTO)))
                .andExpect(status().isNoContent());
        verify(eventService).deleteParticipantFromEvent(eq(participantRequestDTO));
    }

    @Test
    void shouldGetFutureEvents() throws Exception {
        List<Event> futureEvents = new ArrayList<>();
        when(eventService.getFutureEvents()).thenReturn(futureEvents);

        mockMvc.perform(get("/api/events/future"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetPastEvents() throws Exception {
        List<Event> pastEvents = new ArrayList<>();
        when(eventService.getPastEvents()).thenReturn(pastEvents);

        mockMvc.perform(get("/api/events/past"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
