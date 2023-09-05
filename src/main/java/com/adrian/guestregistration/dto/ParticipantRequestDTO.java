package com.adrian.guestregistration.dto;

import com.adrian.guestregistration.enums.ParticipantType;

import lombok.Data;

@Data
public class ParticipantRequestDTO {
  private Long eventId;
  private Long participantId;
  private ParticipantType participantType;
}
