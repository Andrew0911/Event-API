package org.example.proiectlaborator.service;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.OrganizerDto;
import org.example.proiectlaborator.model.Organizer;
import org.example.proiectlaborator.repository.OrganizerRepository;
import org.example.proiectlaborator.utils.OrganizerUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final OrganizerUtil organizerUtil;

    public Organizer addOrganizer(OrganizerDto organizerDto) {
        return organizerRepository.save(organizerUtil.fromDtoToOrganizer(organizerDto));
    }

    public void deleteOrganizer(Integer organizerId) {
        organizerRepository.deleteById(organizerId);
    }
}
