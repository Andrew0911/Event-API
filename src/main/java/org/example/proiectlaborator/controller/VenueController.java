package org.example.proiectlaborator.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.VenueDto;
import org.example.proiectlaborator.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/venue")
@AllArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping("/new")
    public ResponseEntity<?> addVenue(@RequestBody @Valid VenueDto venueDto) {

        var venue = venueService.addVenue(venueDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(venue.getId())
                .toUri();

        return ResponseEntity.created(uri).body(venue);
    }

    @DeleteMapping("/delete/{venueId}")
    public void deleteVenue(@PathVariable("venueId") Integer venueId) {
        venueService.deleteVenue(venueId);
    }

}
