package org.example.proiectlaborator.utils;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.ReviewDto;
import org.example.proiectlaborator.exceptions.GeneralException;
import org.example.proiectlaborator.model.Review;
import org.example.proiectlaborator.repository.EventRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewUtil {

    private final EventRepository eventRepository;

    public Review fromDtoToReview(Integer eventId, ReviewDto reviewDto) {

        // Event validation
        var event = eventRepository.findById(eventId)
                                   .orElseThrow(() -> new GeneralException("Event with ID " + eventId + " not found."));

        return Review.builder()
                    .event(event)
                    .rating(reviewDto.getRating())
                    .comment(reviewDto.getComment())
                    .build();
    }
}
