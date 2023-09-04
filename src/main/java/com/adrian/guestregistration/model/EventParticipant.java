package com.adrian.guestregistration.model;

import com.adrian.guestregistration.enums.ParticipantType;

public interface EventParticipant {

    ParticipantType getType();
    Long getId();
}
