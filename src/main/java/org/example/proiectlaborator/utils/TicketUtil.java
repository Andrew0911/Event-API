package org.example.proiectlaborator.utils;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.TicketDto;
import org.example.proiectlaborator.exceptions.GeneralException;
import org.example.proiectlaborator.model.Attendee;
import org.example.proiectlaborator.model.Ticket;
import org.example.proiectlaborator.repository.AttendeeRepository;
import org.example.proiectlaborator.repository.EventRepository;
import org.example.proiectlaborator.repository.TicketRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class TicketUtil {

    private AttendeeRepository attendeeRepository;
    private EventRepository eventRepository;
    private TicketRepository ticketRepository;

    public Ticket fromDtoToTicket(TicketDto ticketDto) {

        Integer eventId = ticketDto.getEventId();
        String attendeeEmail = ticketDto.getAttendeeEmail();
        String attendeeName = ticketDto.getAttendeeName();
        String attendeePhone = ticketDto.getAttendeePhone();

        // Event validation
        var event = eventRepository.findById(eventId)
                                   .orElseThrow(() -> new GeneralException("Event with ID " + eventId + " not found."));

        // Attendee check
        Attendee attendee = attendeeRepository.findByEmailAndNameAndPhone(attendeeEmail, attendeeName, attendeePhone);

        if(attendee != null) {

            var attendeeTicket = ticketRepository.findTicketByAttendee_IdAndEvent_Id(attendee.getId(), eventId);

            if(attendeeTicket != null) {
                throw new GeneralException("This attendee already has a ticket for the selected event.");
            }

        } else {
            attendee = attendeeRepository.save(
                    Attendee.builder()
                            .email(attendeeEmail)
                            .name(attendeeName)
                            .phone(attendeePhone)
                            .build()
            );
        }

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Bucharest"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return Ticket.builder()
                .attendee(attendee)
                .event(event)
                .emittedAt(now.format(formatter))
                .build();

    }
}
