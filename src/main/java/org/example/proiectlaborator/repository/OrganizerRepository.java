package org.example.proiectlaborator.repository;

import org.example.proiectlaborator.model.Organizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface OrganizerRepository extends JpaRepository<Organizer, Integer>, JpaSpecificationExecutor<Organizer> {}
