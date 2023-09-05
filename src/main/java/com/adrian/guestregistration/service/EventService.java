package com.adrian.guestregistration.service;

import com.adrian.guestregistration.dto.ParticipantRequestDTO;
import com.adrian.guestregistration.enums.ParticipantType;
import com.adrian.guestregistration.model.Company;
import com.adrian.guestregistration.model.Event;
import com.adrian.guestregistration.model.EventParticipant;
import com.adrian.guestregistration.model.Person;
import com.adrian.guestregistration.repo.EventRepo;
import com.adrian.guestregistration.validator.EntityValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepo eventRepo;
    private final PersonService personService;
    private final CompanyService companyService;
    private final EntityValidator entityValidator;

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepo.findById(id);
    }

    public List<EventParticipant> getParticipantsByEventId(Long id) {
        return eventRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found")).getParticipants();
    }

    @Transactional
    public Event addPersonToEvent(Person personDTO, Long id) {
        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Person newPerson = Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .idCode(personDTO.getIdCode())
                .paymentMethod(personDTO.getPaymentMethod())
                .additionalInformation(personDTO.getAdditionalInformation())
                .build();

        personService.createPerson(newPerson);
        event.getPersons().add(newPerson);

        return eventRepo.save(event);
    }

    @Transactional
    public Event addCompanyToEvent(Company companyDTO, Long id) {
        Event event = eventRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Company newCompany = Company.builder()
                .legalName(companyDTO.getLegalName())
                .registryCode(companyDTO.getRegistryCode())
                .numberOfParticipants(companyDTO.getNumberOfParticipants())
                .paymentMethod(companyDTO.getPaymentMethod())
                .additionalInformation(companyDTO.getAdditionalInformation())
                .build();

        companyService.createCompany(newCompany);
        event.getCompanies().add(newCompany);

        return eventRepo.save(event);
    }

    @Transactional
    public void deleteParticipantFromEvent(ParticipantRequestDTO participantRequestDTO) {
        Event event = eventRepo.findById(participantRequestDTO.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if (participantRequestDTO.getParticipantType() == ParticipantType.PERSON) {
            Person person = personService.getPersonById(participantRequestDTO.getParticipantId())
                    .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
            event.getPersons().remove(person);
        } else if (participantRequestDTO.getParticipantType() == ParticipantType.COMPANY) {
            Company company = companyService.getCompanyById(participantRequestDTO.getParticipantId())
                    .orElseThrow(() -> new IllegalArgumentException("Company not found"));
            event.getCompanies().remove(company);
        }
        eventRepo.save(event);
    }

    @Transactional
    public Event createEvent(Event event) {
        // entityValidator.validateEvent(event);
        return eventRepo.save(event);
    }

    @Transactional
    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = eventRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // entityValidator.validateEvent(updatedEvent);

        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setVenue(updatedEvent.getVenue());
        existingEvent.setAdditionalInformation(updatedEvent.getAdditionalInformation());

        return eventRepo.save(existingEvent);
    }

    public List<Event> getFutureEvents() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        return eventRepo.findByDateAfter(currentDate);
    }

    public List<Event> getPastEvents() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        return eventRepo.findByDateBefore(currentDate);
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }
}
