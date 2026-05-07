package com.CSC340.MinervasList.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CSC340.MinervasList.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByUserId(Long userId);

    @Query("SELECT s FROM Seller s WHERE LOWER(s.email) = LOWER(:email)")
    Optional<Seller> findByEmailIgnoreCase(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE LOWER(email) = LOWER(:email) AND user_type = 'SELLER'", nativeQuery = true)
    Optional<Seller> findByEmailIgnoreCaseNative(@Param("email") String email);

    @Query("SELECT COUNT(s) > 0 FROM Seller s WHERE LOWER(s.email) = LOWER(:email)")
    boolean existsByEmailIgnoreCase(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE users SET user_type = 'SELLER' WHERE user_id = :sellerId", nativeQuery = true)
    int forceSellerUserType(@Param("sellerId") Long sellerId);

    boolean existsByUserId(Long userId);

    List<Seller> findByBusinessNameContainingIgnoreCase(String businessName);
}
