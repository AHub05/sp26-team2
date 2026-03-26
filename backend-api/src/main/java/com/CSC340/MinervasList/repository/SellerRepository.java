package com.CSC340.MinervasList.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CSC340.MinervasList.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

    List<Seller> findByBusinessNameContainingIgnoreCase(String businessName);
}