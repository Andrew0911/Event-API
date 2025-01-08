package org.example.proiectlaborator.service;

import org.example.proiectlaborator.dto.VenueDto;
import org.example.proiectlaborator.model.Venue;
import org.example.proiectlaborator.repository.VenueRepository;
import org.example.proiectlaborator.utils.VenueUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VenueServiceTests {

    @Mock
    private VenueRepository venueRepository;

    @Mock
    private VenueUtil venueUtil;

    @InjectMocks
    private VenueService venueService;

    @Test
    void testDeleteVenue() {
        Integer venueId = 1;

        venueService.deleteVenue(venueId);

        verify(venueRepository).deleteById(venueId);
    }

    @Test
    void testAddVenue() {

        var venueDto = VenueDto.builder()
                .name("Venue")
                .location("Location")
                .capacity(1000)
                .build();

        var mockVenue = Venue.builder()
                .id(1)
                .name("Venue")
                .location("Location")
                .capacity(1000)
                .build();

        when(venueUtil.fromDtoToVenue(venueDto)).thenReturn(mockVenue);
        when(venueRepository.save(mockVenue)).thenReturn(mockVenue);

        var result = venueService.addVenue(venueDto);

        assertEquals("Venue", result.getName());
        assertEquals("Location", result.getLocation());
        assertEquals(1000, result.getCapacity());

        verify(venueUtil).fromDtoToVenue(venueDto);
        verify(venueRepository).save(mockVenue);
    }

}
