package org.example.proiectlaborator.service;

import org.example.proiectlaborator.dto.EventDto;
import org.example.proiectlaborator.model.Event;
import org.example.proiectlaborator.model.Organizer;
import org.example.proiectlaborator.model.Venue;
import org.example.proiectlaborator.repository.EventRepository;
import org.example.proiectlaborator.utils.EventUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceTests {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventUtil eventUtil;

    @Test
    void testListEvent() {
        var date = "08-01-2025";

        var event1 = Event.builder()
                .name("Event 1")
                .date(date)
                .build();

        var event2 = Event.builder()
                .name("Event 2")
                .date(date)
                .build();

        var events = Arrays.asList(event1, event2);

        when(eventRepository.findAllByDate(date)).thenReturn(events);

        var result = eventService.listAllEventsByDate(date);

        assertEquals(2, result.size());
        assertEquals("Event 1", result.get(0).getName());
        assertEquals("Event 2", result.get(1).getName());

        verify(eventRepository).findAllByDate(date);
    }

    @Test
    void testAddEvent() {
        var eventDto = EventDto.builder()
                .name("Event 1")
                .baseTicketPrice(100.0)
                .date("03-03-2025")
                .hour("12:00")
                .description("Description")
                .organizerId(1)
                .venueId(1)
                .ticketNumber(200)
                .build();

        var organizer = Organizer.builder()
                .id(1)
                .name("Organizer")
                .contactInfo("Contact Info")
                .build();

        var venue = Venue.builder()
                .id(1)
                .name("Venue")
                .location("Location")
                .capacity(10000)
                .build();

        var mockEvent = Event.builder()
                .id(1)
                .name("Event 1")
                .baseTicketPrice(100.0)
                .ticketPrice(100.0)
                .date("03-03-2025")
                .hour("12:00")
                .description("Description")
                .organizer(organizer)
                .venue(venue)
                .rating(0)
                .ticketNumber(200)
                .build();

        when(eventUtil.fromDtoToEvent(eventDto)).thenReturn(mockEvent);
        when(eventRepository.save(mockEvent)).thenReturn(mockEvent);

        var result = eventService.addEvent(eventDto);

        assertEquals("Event 1", result.getName());
        assertEquals("Description", result.getDescription());
        assertEquals(100, result.getBaseTicketPrice());
        assertEquals(100, result.getTicketPrice());
        /// .....

        verify(eventUtil).fromDtoToEvent(eventDto);
        verify(eventRepository).save(mockEvent);

    }

}
