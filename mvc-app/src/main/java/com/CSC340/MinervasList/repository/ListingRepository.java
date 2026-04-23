package com.CSC340.MinervasList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CSC340.MinervasList.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findByTitle(String title);

    @Query("SELECT l FROM Listing l WHERE l.seller.userId = :sellerId")
    List<Listing> findBySellerId(@Param("sellerId") Long sellerId);

    @Query("SELECT l FROM Listing l WHERE l.category = :category")
    List<Listing> findByCategory(@Param("category") Listing.Category category);

    @Query("SELECT l FROM Listing l WHERE l.itemCondition = :itemCondition")
    List<Listing> findByItemCondition(@Param("itemCondition") Listing.ItemCondition itemCondition);

    @Query("SELECT l FROM Listing l WHERE l.status = :status")
    List<Listing> findByStatus(@Param("status") Listing.ListingStatus status);

}
