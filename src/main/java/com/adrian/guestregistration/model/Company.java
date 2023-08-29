package com.adrian.guestregistration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @NotBlank(message = "Name can't be empty")
    private String legalName;

    @NotBlank(message = "Registry code can't be empty")
    private String registryCode;

    @NotNull
    private Integer numberOfParticipants;

    @Length(max = 5000)
    private String additionalInformation;
}
