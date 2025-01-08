package org.example.proiectlaborator.utils;

import org.example.proiectlaborator.dto.VenueDto;
import org.example.proiectlaborator.model.Venue;
import org.springframework.stereotype.Component;

@Component
public class VenueUtil {

    public Venue fromDtoToVenue(VenueDto venueDto) {

        return Venue.builder()
                .capacity(venueDto.getCapacity())
                .location(venueDto.getLocation())
                .name(venueDto.getName())
                .build();
    }
}
