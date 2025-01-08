package org.example.proiectlaborator.utils;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.ReviewDto;
import org.example.proiectlaborator.exceptions.GeneralException;
import org.example.proiectlaborator.model.Review;
import org.example.proiectlaborator.repository.EventRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
@AllArgsConstructor
public class ReviewUtil {

    private final EventRepository eventRepository;

    public Review fromDtoToReview(Integer eventId, ReviewDto reviewDto) {

        // Event validation
        var event = eventRepository.findById(eventId)
                                   .orElseThrow(() -> new GeneralException("Event with ID " + eventId + " not found."));

        var eventDate = event.getDate();

        if(isCurrentDateMoreThan7DaysInFutureOf(eventDate)) {
          throw new GeneralException("Reviews can no longer be posted on this event.");
        }

        return Review.builder()
                    .event(event)
                    .rating(reviewDto.getRating())
                    .comment(reviewDto.getComment())
                    .build();
    }

    public static boolean isCurrentDateMoreThan7DaysInFutureOf(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate givenDate = LocalDate.parse(inputDate, formatter);
        LocalDate currentDate = LocalDate.now();
        long daysDifference = ChronoUnit.DAYS.between(givenDate, currentDate);

        return daysDifference > 7;
    }
}
