package org.example.proiectlaborator.service;

import org.example.proiectlaborator.dto.TicketDto;
import org.example.proiectlaborator.model.Attendee;
import org.example.proiectlaborator.model.Ticket;
import org.example.proiectlaborator.repository.TicketRepository;
import org.example.proiectlaborator.utils.EventUtil;
import org.example.proiectlaborator.utils.TicketUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TicketServiceTests {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private EventUtil eventUtil;

    @Mock
    private EventService eventService;

    @Mock
    private TicketUtil ticketUtil;

    @Test
    void testAddTicketToEvent() {

        var ticketDto = TicketDto.builder()
                .eventId(1)
                .attendeeEmail("attendee.email@gmail.com")
                .attendeeName("Attendee Name")
                .attendeePhone("0741231450")
                .build();

        var attendee = Attendee.builder()
                .id(1)
                .email("attendee.email@gmail.com")
                .name("Attendee Name")
                .phone("0741231450")
                .build();

        var mockTicket = Ticket.builder()
                .id(1)
                .attendee(attendee)
                .emittedAt("08-01-2025 12:30")
                .build();

        when(ticketUtil.fromDtoToTicket(ticketDto)).thenReturn(mockTicket);
        when(ticketRepository.save(mockTicket)).thenReturn(mockTicket);

        var result = ticketService.addTicket(ticketDto);

        assertEquals("attendee.email@gmail.com", result.getAttendee().getEmail());
        assertEquals("Attendee Name", result.getAttendee().getName());
        assertEquals("0741231450", result.getAttendee().getPhone());

        verify(ticketUtil).fromDtoToTicket(ticketDto);
        verify(ticketRepository).save(mockTicket);

    }
}
