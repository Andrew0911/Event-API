package org.example.proiectlaborator.repository;

import org.example.proiectlaborator.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer>, JpaSpecificationExecutor<Organizer> {}
