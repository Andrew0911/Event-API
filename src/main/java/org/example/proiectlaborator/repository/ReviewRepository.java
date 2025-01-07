package org.example.proiectlaborator.repository;

import org.example.proiectlaborator.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer>, JpaSpecificationExecutor<Review> {

    List<Review> findAllByEvent_Id(Integer eventId);

}
