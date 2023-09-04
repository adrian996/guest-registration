package com.adrian.guestregistration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Length(max = 128)
    private String name;

    @NotNull
    @Future
    private Timestamp date;

    @NotBlank(message = "Venue can't be empty")
    @Length(max = 128)
    private String venue;

    @Length(max = 1500)
    private String additionalInformation;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "event_company",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Company> companies = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "event_person",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons = new ArrayList<>();

    @Transient
    @JsonIgnore
    private List<EventParticipant> participants = new ArrayList<>();

    @PostLoad
    private void populateParticipants() {
        participants.addAll(companies);
        participants.addAll(persons);
    }

}

