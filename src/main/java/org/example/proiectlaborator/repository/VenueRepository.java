package org.example.proiectlaborator.repository;

import org.example.proiectlaborator.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VenueRepository extends JpaRepository<Venue, Integer>, JpaSpecificationExecutor<Venue> {}