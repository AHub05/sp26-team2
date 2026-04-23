package com.CSC340.MinervasList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CSC340.MinervasList.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findByTitle(String title);

    @Query(value = "SELECT l.* FROM listings l WHERE l.seller_id = :sellerId", nativeQuery = true)
    List<Listing> findBySellerId(Long id);

    @Query(value = "SELECT l.* FROM listing l WHERE l.category = :category", nativeQuery = true)
    List<Listing> findByCategory(String category);

    @Query(value = "SELECT l.* FROM listing l WHERE l.itemCondition = :itemCondition", nativeQuery = true)
    List<Listing> findByItemCondition(String condtion);

    @Query(value = "SELECT l.* FROM listing l WHERE l.status = :status", nativeQuery = true)
    List<Listing> findByStatus(String status);

}