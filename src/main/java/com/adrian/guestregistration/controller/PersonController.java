package com.adrian.guestregistration.controller;

import com.adrian.guestregistration.model.Person;
import com.adrian.guestregistration.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        log.info("Getting all persons");
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        log.info("Getting person by id " + id);
        Optional<Person> person = personService.getPersonById(id);
        return person.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        log.info("Creating new person: {}", person);
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        log.info("Updating person: {}", updatedPerson);
        Person updated = personService.updatePerson(id, updatedPerson);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.info("Deleting person with id " + id);
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
