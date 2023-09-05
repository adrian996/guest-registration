package com.adrian.guestregistration.model;

import com.adrian.guestregistration.enums.ParticipantType;
import com.adrian.guestregistration.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company implements EventParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be empty")
    private String legalName;

    @NotBlank(message = "Registry code can't be empty")
    private String registryCode;

    @NotNull
    private Integer numberOfParticipants;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Length(max = 5000)
    private String additionalInformation;

    @Override
    public ParticipantType getType() {
        return ParticipantType.COMPANY;
    }
}
