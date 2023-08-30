package com.adrian.guestregistration.repo;

import com.adrian.guestregistration.model.Event;
import com.adrian.guestregistration.model.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
    @Query(value = "SELECT DISTINCT " +
            "CASE " +
            "    WHEN ep.participant_type = 'COMPANY' THEN c.id " +
            "    WHEN ep.participant_type = 'PERSON' THEN p.id " +
            "END AS participant_id, " +
            "CASE " +
            "    WHEN ep.participant_type = 'COMPANY' THEN 'COMPANY' " +
            "    WHEN ep.participant_type = 'PERSON' THEN 'PERSON' " +
            "END AS participant_type, " +
            "CASE " +
            "    WHEN ep.participant_type = 'COMPANY' THEN c.legal_name " +
            "    WHEN ep.participant_type = 'PERSON' THEN CONCAT(p.first_name, ' ', p.last_name) " +
            "END AS participant_name " +
            "FROM " +
            "event_participant ep " +
            "LEFT JOIN companies c ON ep.participant_id = c.id AND ep.participant_type = 'COMPANY' " +
            "LEFT JOIN persons p ON ep.participant_id = p.id AND ep.participant_type = 'PERSON' " +
            "WHERE " +
            "ep.event_id = :eventId", nativeQuery = true)
    List<EventParticipant> findAllParticipantsByEventId(@Param("eventId") Long eventId);

}
