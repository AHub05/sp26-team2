package com.CSC340.MinervasList.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CSC340.MinervasList.entity.Review;
import com.CSC340.MinervasList.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);

        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(review);
    }

    @GetMapping("/customer/{customerId}")
    public List<Review> getReviewsByCustomerId(@PathVariable Long customerId) {
        return reviewService.getReviewsByCustomerId(customerId);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Review> getReviewsBySellerId(@PathVariable Long sellerId) {
        return reviewService.getReviewsBySellerId(sellerId);
    }

    @PostMapping("/customer/{customerId}/seller/{sellerId}")
    public ResponseEntity<Review> createReview(@PathVariable Long customerId,
                                               @PathVariable Long sellerId,
                                               @RequestBody Review review) {
        Review createdReview = reviewService.createReview(customerId, sellerId, review);

        if (createdReview == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review updatedReview) {
        Review review = reviewService.updateReview(reviewId, updatedReview);

        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}