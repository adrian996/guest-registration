package com.adrian.guestregistration.controller;

import com.adrian.guestregistration.enums.PaymentMethod;
import com.adrian.guestregistration.model.Person;
import com.adrian.guestregistration.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PersonControllerTest {

    @Mock
    private PersonService personService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        PersonController personController = new PersonController(personService);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldGetAllPersons() throws Exception {
        List<Person> persons = new ArrayList<>();
        when(personService.getAllPersons()).thenReturn(persons);

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetPersonById() throws Exception {
        Long personId = 1L;
        Person person = new Person();
        person.setId(personId);
        when(personService.getPersonById(personId)).thenReturn(Optional.of(person));

        mockMvc.perform(get("/api/persons/{id}", personId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(personId));
    }

    @Test
    void shouldCreatePerson() throws Exception {
        Person person = Person.builder().
                firstName("person").
                lastName("lastname").
                idCode("39606052784").
                paymentMethod(PaymentMethod.BANK_TRANSFER).
                build();

        when(personService.createPerson(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdatePerson() throws Exception {
        Long personId = 1L;
        Person updatedPerson = Person.builder().
                firstName("person").
                lastName("lastname").
                idCode("39606052784").
                paymentMethod(PaymentMethod.BANK_TRANSFER).
                build();
        updatedPerson.setId(personId);
        when(personService.updatePerson(eq(personId), any(Person.class))).thenReturn(updatedPerson);

        mockMvc.perform(put("/api/persons/{id}", personId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPerson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(personId));
    }

    @Test
    void shouldDeletePerson() throws Exception {
        Long personId = 1L;
        doNothing().when(personService).deletePerson(personId);

        mockMvc.perform(delete("/api/persons/{id}", personId))
                .andExpect(status().isNoContent());
    }
}
