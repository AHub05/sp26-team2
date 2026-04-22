package com.CSC340.MinervasList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CSC340.MinervasList.entity.Customer;
import com.CSC340.MinervasList.entity.Review;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.repository.CustomerRepository;
import com.CSC340.MinervasList.repository.ReviewRepository;
import com.CSC340.MinervasList.repository.SellerRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         CustomerRepository customerRepository,
                         SellerRepository sellerRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public List<Review> getReviewsByCustomerId(Long customerId) {
        return reviewRepository.findByCustomerUserId(customerId);
    }

    public List<Review> getReviewsBySellerId(Long sellerId) {
        return reviewRepository.findBySellerUserId(sellerId);
    }

    public Review createReview(Long customerId, Long sellerId, Review review) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);

        if (customerOptional.isEmpty() || sellerOptional.isEmpty()) {
            return null;
        }

        review.setCustomer(customerOptional.get());
        review.setSeller(sellerOptional.get());

        return reviewRepository.save(review);
    }

    public Review updateReview(Long reviewId, Review updatedReview) {
        Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);

        if (existingReviewOptional.isEmpty()) {
            return null;
        }

        Review existingReview = existingReviewOptional.get();
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());

        return reviewRepository.save(existingReview);
    }

    public boolean deleteReview(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

        if (reviewOptional.isEmpty()) {
            return false;
        }

        reviewRepository.deleteById(reviewId);
        return true;
    }
}