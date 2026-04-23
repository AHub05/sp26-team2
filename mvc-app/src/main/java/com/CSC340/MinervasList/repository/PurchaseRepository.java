package com.CSC340.MinervasList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CSC340.MinervasList.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByCustomerUserId(Long userId);

    List<Purchase> findByListingListingId(Long listingId);
}
