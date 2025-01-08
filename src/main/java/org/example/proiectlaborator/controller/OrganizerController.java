package org.example.proiectlaborator.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.OrganizerDto;
import org.example.proiectlaborator.service.OrganizerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/organizer")
@AllArgsConstructor
public class OrganizerController {

    private final OrganizerService organizerService;

    @PostMapping("/new")
    public ResponseEntity<?> addOrganizer(@RequestBody @Valid OrganizerDto organizerDto) {

        var organizer = organizerService.addOrganizer(organizerDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(organizer.getId())
                .toUri();

        return ResponseEntity.created(uri).body(organizer);
    }

    @DeleteMapping("/delete/{organizerId}")
    public void deleteOrganizer(@PathVariable("organizerId") Integer organizerId) {
        organizerService.deleteOrganizer(organizerId);
    }
}
