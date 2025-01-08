package org.example.proiectlaborator.service;

import org.example.proiectlaborator.dto.ReviewDto;
import org.example.proiectlaborator.model.Review;
import org.example.proiectlaborator.repository.ReviewRepository;
import org.example.proiectlaborator.utils.ReviewUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReviewServiceTests {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private EventService eventService;

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewUtil reviewUtil;

    @Test
    void testDeleteReview() {
        Integer eventId = 1;
        Integer reviewId = 1;

        reviewService.deleteReview(eventId, reviewId);

        verify(reviewRepository).deleteById(reviewId);

        verify(eventService).updateRating(eventId);
    }

    @Test
    void testEditReview() {
        Integer eventId = 1;
        Integer reviewId = 1;

        var reviewDto = ReviewDto.builder()
                .comment("Updated review comment")
                .rating(4)
                .build();

        var existingReview = Review.builder()
                .id(reviewId)
                .comment("Old Review Comment")
                .rating(4)
                .build();

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(existingReview)).thenReturn(existingReview);

        var updatedReview = reviewService.editReview(eventId, reviewId, reviewDto);

        verify(reviewRepository).findById(reviewId);
        verify(reviewRepository).save(updatedReview);

        assertEquals("Updated review comment", updatedReview.getComment());
        assertEquals(4, updatedReview.getRating());

        verify(eventService).updateRating(eventId);
    }

    @Test
    void testListEventReviews() {
        Integer eventId = 1;

        var review1 = Review.builder().id(1).comment("Review 1").rating(4).build();
        var review2 = Review.builder().id(2).comment("Review 2").rating(4).build();

        var mockReviews = Arrays.asList(review1, review2);

        when(reviewRepository.findAllByEvent_Id(eventId)).thenReturn(mockReviews);

        var result = reviewService.listAllEventReviews(eventId);

        assertEquals(2, result.size());
        assertEquals("Review 1", result.get(0).getComment());
        assertEquals("Review 2", result.get(1).getComment());

        verify(reviewRepository, times(1)).findAllByEvent_Id(eventId);
    }

    @Test
    void testAddReviewToEvent() {

        Integer eventId = 1;

        var reviewDto = ReviewDto.builder()
                .comment("Comment")
                .rating(5)
                .build();

        var mockReview = Review.builder()
                .id(1)
                .comment("Comment")
                .rating(5)
                .build();

        when(reviewUtil.fromDtoToReview(eventId, reviewDto)).thenReturn(mockReview);
        when(reviewRepository.save(mockReview)).thenReturn(mockReview);

        var result = reviewService.addReviewToEvent(eventId, reviewDto);

        assertEquals("Comment", result.getComment());
        assertEquals(5, result.getRating());

        verify(reviewUtil).fromDtoToReview(eventId, reviewDto);
        verify(reviewRepository).save(mockReview);

    }

}
