package org.example.proiectlaborator.service;

import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.ReviewDto;
import org.example.proiectlaborator.exceptions.GeneralException;
import org.example.proiectlaborator.model.Review;
import org.example.proiectlaborator.repository.ReviewRepository;
import org.example.proiectlaborator.utils.ReviewUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EventService eventService;
    private final ReviewUtil reviewUtil;

    public List<Review> listAllEventReviews(Integer eventId) {
        return reviewRepository.findAllByEvent_Id(eventId);
    }

    public Review addReviewToEvent(Integer eventId, ReviewDto reviewDto) {
        var review = reviewRepository.save(reviewUtil.fromDtoToReview(eventId, reviewDto));

        eventService.updateRating(eventId);

        return review;
    }

    public void deleteReview(Integer eventId, Integer reviewId) {
        reviewRepository.deleteById(reviewId);
        eventService.updateRating(eventId);
    }

    public Review editReview(Integer eventId, Integer reviewId, ReviewDto reviewDto) {
        var review = reviewRepository.findById(reviewId).orElseThrow(() -> new GeneralException("Review with ID " + reviewId + " not found."));

        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());

        review = reviewRepository.save(review);
        eventService.updateRating(eventId);

        return review;
    }

}
