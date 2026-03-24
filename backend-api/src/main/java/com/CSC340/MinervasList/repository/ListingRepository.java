package com.CSC340.MinervasList.repository;

import com.CSC340.MinervasList.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
}