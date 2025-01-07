package org.example.proiectlaborator.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.TicketDto;
import org.example.proiectlaborator.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/new")
    public ResponseEntity<?> addTicket(@RequestBody @Valid TicketDto ticketDto) {

        var ticket = ticketService.addTicket(ticketDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ticket.getId())
                .toUri();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("eventName", ticket.getEvent().getName());
        responseBody.put("eventDate", ticket.getEvent().getDate());
        responseBody.put("eventHour", ticket.getEvent().getHour());
        responseBody.put("ticketPrice", ticket.getEvent().getTicketPrice());
        responseBody.put("nameOnTicket", ticket.getAttendee().getName());
        responseBody.put("emittedAt", ticket.getEmittedAt());

        return ResponseEntity.created(uri).body(responseBody);
    }

}
