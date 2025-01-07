package org.example.proiectlaborator.service;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.TicketDto;
import org.example.proiectlaborator.model.Ticket;
import org.example.proiectlaborator.repository.TicketRepository;
import org.example.proiectlaborator.utils.EventUtil;
import org.example.proiectlaborator.utils.TicketUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {

    private final EventUtil eventUtil;
    private TicketRepository ticketRepository;
    private EventService eventService;
    private final TicketUtil ticketUtil;

    public Ticket addTicket(TicketDto ticketDto){

        var eventId = ticketDto.getEventId();
        eventUtil.checkAvailableTickets(eventId);

        var ticket = ticketRepository.save(ticketUtil.fromDtoToTicket(ticketDto));
        eventService.updateTicketPrice(eventId);
        return ticket;
    }

}
