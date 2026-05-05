package com.CSC340.MinervasList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CSC340.MinervasList.dto.SellerStatsDto;
import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.repository.ListingRepository;
import com.CSC340.MinervasList.repository.PurchaseRepository;
import com.CSC340.MinervasList.repository.ReviewRepository;
import com.CSC340.MinervasList.repository.SellerRepository;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ListingRepository listingRepository;
    private final PurchaseRepository purchaseRepository;
    private final ReviewRepository reviewRepository;

    public SellerService(SellerRepository sellerRepository,
                         ListingRepository listingRepository,
                         PurchaseRepository purchaseRepository,
                         ReviewRepository reviewRepository) {
        this.sellerRepository = sellerRepository;
        this.listingRepository = listingRepository;
        this.purchaseRepository = purchaseRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));
    }

    public List<Seller> searchByBusinessName(String businessName) {
        return sellerRepository.findByBusinessNameContainingIgnoreCase(businessName);
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long sellerId, Seller updatedSeller) {
        Seller existingSeller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        existingSeller.setBusinessName(updatedSeller.getBusinessName());
        existingSeller.setEmail(updatedSeller.getEmail());
        existingSeller.setPassword(updatedSeller.getPassword());
        existingSeller.setProfilePhotoUrl(updatedSeller.getProfilePhotoUrl());
        existingSeller.setBio(updatedSeller.getBio());
        existingSeller.setServicesOffered(updatedSeller.getServicesOffered());

        return sellerRepository.save(existingSeller);
    }

    public void deleteSeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        sellerRepository.delete(seller);
    }

    public Seller getSellerByUserId(Long userId) {
        return sellerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Seller not found with user ID: " + userId));
    }

    public SellerStatsDto getSellerStats(Long sellerId) {
        getSellerById(sellerId);

        long totalListings = listingRepository.countBySellerUserId(sellerId);
        long activeListings = listingRepository.countBySellerUserIdAndStatus(sellerId, Listing.ListingStatus.AVAILABLE);
        long completedTransactions = purchaseRepository.countByListingSellerUserId(sellerId);
        long totalReviews = reviewRepository.countBySellerUserId(sellerId);
        Double averageRatingValue = reviewRepository.findAverageRatingBySellerId(sellerId);
        double averageRating = averageRatingValue == null ? 0.0 : averageRatingValue;

        SellerStatsDto stats = new SellerStatsDto();
        stats.setSellerId(sellerId);
        stats.setTotalListings(totalListings);
        stats.setActiveListings(activeListings);
        stats.setCompletedTransactions(completedTransactions);
        stats.setTotalReviews(totalReviews);
        stats.setAverageRating(averageRating);
        // TODO: add messagesReceived when a Message entity exists in the project.
        return stats;
    }
}
