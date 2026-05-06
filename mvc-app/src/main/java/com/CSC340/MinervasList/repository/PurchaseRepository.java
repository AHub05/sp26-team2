package com.CSC340.MinervasList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CSC340.MinervasList.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = "SELECT p.* FROM purchases p WHERE p.customer_id = :customerId", nativeQuery = true)
    List<Purchase> findByUserId(Long customerId);

    List<Purchase> findByListingListingId(Long listingId);

    long countByListingSellerUserId(Long sellerId);
}
