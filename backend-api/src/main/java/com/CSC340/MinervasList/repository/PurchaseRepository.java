package com.CSC340.MinervasList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CSC340.MinervasList.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByCustomerCustomerId(Long customerId);

    List<Purchase> findByListingListingId(Long listingId);
}