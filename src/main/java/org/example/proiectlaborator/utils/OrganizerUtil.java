package org.example.proiectlaborator.utils;

import org.example.proiectlaborator.dto.OrganizerDto;
import org.example.proiectlaborator.model.Organizer;
import org.springframework.stereotype.Component;

@Component
public class OrganizerUtil {

    public Organizer fromDtoToOrganizer(OrganizerDto organizerDto) {

        return Organizer.builder()
                .contactInfo(organizerDto.getContactInfo())
                .name(organizerDto.getName())
                .build();
    }
}
