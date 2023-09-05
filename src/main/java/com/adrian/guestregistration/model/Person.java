package com.adrian.guestregistration.model;

import com.adrian.guestregistration.enums.ParticipantType;
import com.adrian.guestregistration.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person implements EventParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name can't be empty")
    private String firstName;

    @NotBlank(message = "Last name can't be empty")
    private String lastName;

    @NotBlank(message = "Id code can't be empty")
    private String idCode;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Length(max = 1500)
    private String additionalInformation;

    @Override
    public ParticipantType getType() {
        return ParticipantType.PERSON;
    }
}
