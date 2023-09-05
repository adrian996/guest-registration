package com.adrian.guestregistration.service;

import com.adrian.guestregistration.model.Person;
import com.adrian.guestregistration.repo.PersonRepo;
import com.adrian.guestregistration.validator.EntityValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;

    @Mock
    private EntityValidator entityValidator;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Person> mockPersons = new ArrayList<>();
        Person mockPerson = new Person();
        mockPerson.setId(1L);
        mockPerson.setFirstName("John");
        mockPerson.setLastName("Doe");
        mockPersons.add(mockPerson);

        when(personRepo.findAll()).thenReturn(mockPersons);
        when(personRepo.findById(1L)).thenReturn(Optional.of(mockPerson));
    }

    @Test
    void shouldGetAllPersons() {
        List<Person> persons = personService.getAllPersons();
        assertEquals(1, persons.size());
    }

    @Test
    void shouldGetPersonById() {
        Optional<Person> personOptional = personService.getPersonById(1L);
        assertTrue(personOptional.isPresent());
        assertEquals("John", personOptional.get().getFirstName());
        assertEquals("Doe", personOptional.get().getLastName());
    }

    @Test
    void shouldCreatePerson() {
        Person newPerson = new Person();
        when(personRepo.save(newPerson)).thenReturn(newPerson);

        Person createdPerson = personService.createPerson(newPerson);
        assertNotNull(createdPerson);
        verify(entityValidator).validatePerson(newPerson); // Verify that validation was called
    }

    @Test
    void shouldUpdatePerson() {
        Person existingPerson = new Person();
        existingPerson.setId(1L);
        when(personRepo.findById(1L)).thenReturn(Optional.of(existingPerson));

        Person updatedPerson = new Person();
        updatedPerson.setFirstName("UpdatedFirstName");

        when(personRepo.save(existingPerson)).thenReturn(updatedPerson);

        Person result = personService.updatePerson(1L, updatedPerson);
        assertEquals("UpdatedFirstName", result.getFirstName());
        verify(entityValidator).validatePerson(updatedPerson); // Verify that validation was called
    }

    @Test
    void shouldDeletePerson() {
        personService.deletePerson(1L);
        verify(personRepo).deleteById(1L);
    }
}
