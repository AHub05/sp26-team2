package com.CSC340.MinervasList.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CSC340.MinervasList.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByCustomerUserId(Long userId);

    List<Review> findBySellerUserId(Long userId);

    long countBySellerUserId(Long userId);

    Optional<Review> findByReviewIdAndSellerUserId(Long reviewId, Long sellerId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.seller.userId = :sellerId")
    Double findAverageRatingBySellerId(Long sellerId);
}
