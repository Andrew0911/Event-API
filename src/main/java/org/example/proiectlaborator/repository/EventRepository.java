package org.example.proiectlaborator.repository;

import org.example.proiectlaborator.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {

    List<Event> findAllByDate(String date);

}
