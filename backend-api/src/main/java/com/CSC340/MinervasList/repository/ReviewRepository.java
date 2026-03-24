package com.CSC340.MinervasList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository extends JpaRepository<Review, Long> {
    
    @Query(value = "SELECT r.* FROM reviews WHERE r.customer_id = :customerId", nativeQuery = true)
    List<Review> findByCustomerId(Long id);
}
