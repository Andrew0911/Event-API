package org.example.proiectlaborator.utils;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.EventDto;
import org.example.proiectlaborator.exceptions.GeneralException;
import org.example.proiectlaborator.model.Event;
import org.example.proiectlaborator.repository.EventRepository;
import org.example.proiectlaborator.repository.OrganizerRepository;
import org.example.proiectlaborator.repository.VenueRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class EventUtil {

    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository ;
    private final EventRepository eventRepository;

    public Event fromDtoToEvent(EventDto eventDto) {

        var organizer = organizerRepository.findById(eventDto.getOrganizerId())
                                           .orElseThrow(() -> new GeneralException("Organizer with ID " + eventDto.getOrganizerId() + " not found."));

        var venue = venueRepository.findById(eventDto.getVenueId())
                                   .orElseThrow(() -> new GeneralException("Venue with ID " + eventDto.getVenueId() + " not found."));

        if(eventDto.getTicketNumber() > venue.getCapacity()) {
            throw new GeneralException("Requested ticket quantity exceeds the venue's capacity of " + venue.getCapacity() + " tickets.");
        }

        return Event.builder()
                .date(eventDto.getDate())
                .hour(eventDto.getHour())
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .baseTicketPrice(eventDto.getBaseTicketPrice())
                .ticketPrice(eventDto.getBaseTicketPrice())
                .ticketNumber(eventDto.getTicketNumber())
                .organizer(organizer)
                .venue(venue)
                .reviews(Collections.emptyList())
                .tickets(Collections.emptyList())
                .rating(0)
                .build();
    }

    public void checkAvailableTickets(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new GeneralException("Event with ID " + eventId + " not found."));

        if(event.getTickets().size() >= event.getTicketNumber()){
            throw new GeneralException("Tickets for this event are no longer available.");
        }
    }

}
