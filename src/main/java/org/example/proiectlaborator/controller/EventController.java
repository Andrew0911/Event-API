package org.example.proiectlaborator.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.EventDto;
import org.example.proiectlaborator.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/list")
    public ResponseEntity<?> listEventsByDate(@RequestParam String date) {
        return ResponseEntity.ok().body(eventService.listAllEventsByDate(date));
    }

    @PostMapping("/new")
    public ResponseEntity<?> addEvent(@RequestBody @Valid EventDto eventDto) {

        var event = eventService.addEvent(eventDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(event.getId())
                .toUri();

        return ResponseEntity.created(uri).body(event);
    }

}
