package org.example.proiectlaborator.service;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.VenueDto;
import org.example.proiectlaborator.model.Venue;
import org.example.proiectlaborator.repository.VenueRepository;
import org.example.proiectlaborator.utils.VenueUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueUtil venueUtil;

    public Venue addVenue(VenueDto venueDto) {
        return venueRepository.save(venueUtil.fromDtoToVenue(venueDto));
    }

    public void deleteVenue(Integer venueId) {
        venueRepository.deleteById(venueId);
    }
}
