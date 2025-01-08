package org.example.proiectlaborator.service;

import org.example.proiectlaborator.dto.OrganizerDto;
import org.example.proiectlaborator.model.Organizer;
import org.example.proiectlaborator.repository.OrganizerRepository;
import org.example.proiectlaborator.utils.OrganizerUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrganizerServiceTests {

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private OrganizerUtil organizerUtil;

    @InjectMocks
    private OrganizerService organizerService;

    @Test
    void testDeleteOrganizer() {
        Integer organizerId = 1;

        organizerService.deleteOrganizer(organizerId);

        verify(organizerRepository).deleteById(organizerId);
    }

    @Test
    void testAddOrganizer() {

        var organizerDto = OrganizerDto.builder()
                .name("Organizer")
                .contactInfo("Contact Info")
                .build();

        var mockOrganizer = Organizer.builder()
                .name("Organizer")
                .contactInfo("Contact Info")
                .build();

        when(organizerUtil.fromDtoToOrganizer(organizerDto)).thenReturn(mockOrganizer);
        when(organizerRepository.save(mockOrganizer)).thenReturn(mockOrganizer);

        var result = organizerService.addOrganizer(organizerDto);

        assertEquals("Organizer", result.getName());
        assertEquals("Contact Info", result.getContactInfo());

        verify(organizerUtil).fromDtoToOrganizer(organizerDto);
        verify(organizerRepository).save(mockOrganizer);
    }
}
