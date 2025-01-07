package org.example.proiectlaborator.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.proiectlaborator.dto.ReviewDto;
import org.example.proiectlaborator.model.Review;
import org.example.proiectlaborator.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/list/{eventId}")
    public ResponseEntity<?> listEventReviews(@PathVariable("eventId") Integer eventId) {
        return ResponseEntity.ok().body(reviewService.listAllEventReviews(eventId));
    }

    @PostMapping("/new/{eventId}")
    public ResponseEntity<?> addReviewToEvent(@PathVariable("eventId") Integer eventId, @RequestBody @Valid ReviewDto reviewDto) {

        var review = reviewService.addReviewToEvent(eventId, reviewDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(review.getId())
                .toUri();

        return ResponseEntity.created(uri).body(review);
    }

    @DeleteMapping("/delete/{eventId}/{reviewId}")
    public void deleteReview(@PathVariable("eventId") Integer eventId, @PathVariable("reviewId") Integer reviewId) {
        reviewService.deleteReview(eventId, reviewId);
    }

    @PatchMapping("/edit/{eventId}/{reviewId}")
    public Review editReview(@PathVariable("eventId") Integer eventId, @PathVariable("reviewId") Integer reviewId, @RequestBody @Valid ReviewDto reviewDto) {
        return reviewService.editReview(eventId, reviewId, reviewDto);
    }

}
