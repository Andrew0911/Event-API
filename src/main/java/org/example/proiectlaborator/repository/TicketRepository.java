package org.example.proiectlaborator.repository;

import org.example.proiectlaborator.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

    Ticket findTicketByAttendee_IdAndEvent_Id(Integer attendeeId, Integer eventId);
}
