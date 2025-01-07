package org.example.proiectlaborator.repository;

import org.example.proiectlaborator.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer>, JpaSpecificationExecutor<Attendee> {

    Attendee findByEmailAndNameAndPhone(String email, String name, String phone);
}
