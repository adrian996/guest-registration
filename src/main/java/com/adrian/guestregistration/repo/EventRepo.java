package com.adrian.guestregistration.repo;

import com.adrian.guestregistration.model.Event;
import com.adrian.guestregistration.model.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByDateAfter(Timestamp date);
    List<Event> findByDateBefore(Timestamp date);
}
