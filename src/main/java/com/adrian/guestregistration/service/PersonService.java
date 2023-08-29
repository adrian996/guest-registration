package com.adrian.guestregistration.service;

import com.adrian.guestregistration.model.Person;
import com.adrian.guestregistration.repo.PersonRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.adrian.guestregistration.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepo personRepo;
    private final EntityValidator entityValidator;

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepo.findById(id);
    }

    @Transactional
    public Person createPerson(Person person) {
        entityValidator.validatePerson(person);
        return personRepo.save(person);
    }

    @Transactional
    public Person updatePerson(Long id, Person updatedPerson) {
        Person existingPerson = personRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person not found"));

        entityValidator.validatePerson(updatedPerson);

        existingPerson.setFirstName(updatedPerson.getFirstName());
        existingPerson.setLastName(updatedPerson.getLastName());
        existingPerson.setIdCode(updatedPerson.getIdCode());
        existingPerson.setPaymentMethod(updatedPerson.getPaymentMethod());
        existingPerson.setAdditionalInformation(updatedPerson.getAdditionalInformation());

        return personRepo.save(existingPerson);
    }

    @Transactional
    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }

}
