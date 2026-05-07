package com.CSC340.MinervasList.service;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.CSC340.MinervasList.dto.SellerStatsDto;
import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.repository.ListingRepository;
import com.CSC340.MinervasList.repository.PurchaseRepository;
import com.CSC340.MinervasList.repository.ReviewRepository;
import com.CSC340.MinervasList.repository.SellerRepository;
import com.CSC340.MinervasList.repository.UserRepository;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ListingRepository listingRepository;
    private final PurchaseRepository purchaseRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public SellerService(SellerRepository sellerRepository,
                         ListingRepository listingRepository,
                         PurchaseRepository purchaseRepository,
                         ReviewRepository reviewRepository,
                         UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.listingRepository = listingRepository;
        this.purchaseRepository = purchaseRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));
    }

    public Seller getSellerByEmail(String email) {
        String normalizedEmail = normalizeEmail(email);

        return sellerRepository.findByEmailIgnoreCase(normalizedEmail)
                .or(() -> sellerRepository.findByEmailIgnoreCaseNative(normalizedEmail))
                .or(() -> userRepository.findByEmailIgnoreCase(normalizedEmail)
                        .filter(Seller.class::isInstance)
                        .map(Seller.class::cast))
                .orElseThrow(() -> new RuntimeException("Seller not found with email: " + email));
    }

    public String getUserTypeForEmail(String email) {
        return userRepository.findUserTypeByEmailIgnoreCase(normalizeEmail(email))
                .orElse("MISSING");
    }

    public List<Seller> searchByBusinessName(String businessName) {
        return sellerRepository.findByBusinessNameContainingIgnoreCase(businessName);
    }

    public Seller createSeller(Seller seller) {
        if (seller.getBusinessName() == null || seller.getBusinessName().isBlank()) {
            throw new RuntimeException("Business name is required.");
        }
        if (seller.getPassword() == null || seller.getPassword().isBlank()) {
            throw new RuntimeException("Password is required.");
        }

        String normalizedEmail = normalizeEmail(seller.getEmail());
        if (normalizedEmail.isBlank()) {
            throw new RuntimeException("Email is required.");
        }
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new RuntimeException("A user with that email already exists.");
        }

        seller.setEmail(normalizedEmail);
        seller.setPassword(seller.getPassword().trim());
        seller.setBusinessName(seller.getBusinessName().trim());
        return persistAndForceSellerType(seller);
    }

    public Seller updateSeller(Long sellerId, Seller updatedSeller) {
        Seller existingSeller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        String normalizedEmail = normalizeEmail(updatedSeller.getEmail());
        if (normalizedEmail.isBlank()) {
            throw new RuntimeException("Email is required.");
        }
        if (!existingSeller.getEmail().equalsIgnoreCase(normalizedEmail)
                && userRepository.existsByEmail(normalizedEmail)) {
            throw new RuntimeException("A user with that email already exists.");
        }

        existingSeller.setBusinessName(updatedSeller.getBusinessName() == null ? null : updatedSeller.getBusinessName().trim());
        existingSeller.setEmail(normalizedEmail);
        existingSeller.setPassword(updatedSeller.getPassword() == null ? null : updatedSeller.getPassword().trim());
        existingSeller.setProfilePhotoUrl(updatedSeller.getProfilePhotoUrl());
        existingSeller.setBio(updatedSeller.getBio());
        existingSeller.setServicesOffered(updatedSeller.getServicesOffered());

        return persistAndForceSellerType(existingSeller);
    }

    @Transactional
    public Seller updateSellerWithPhoto(Long sellerId, Seller updatedSeller, MultipartFile profilePhoto) {
        Seller existingSeller = updateSeller(sellerId, updatedSeller);
        applyProfilePhoto(existingSeller, profilePhoto);
        return existingSeller;
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

    @Transactional
    public Seller createSellerWithPhoto(Seller seller, MultipartFile profilePhoto) {
        prepareSellerForSave(seller);
        applyProfilePhoto(seller, profilePhoto);
        return saveSellerWithPhotoFallback(seller, profilePhoto);
    }

    private void applyProfilePhoto(Seller seller, MultipartFile profilePhoto) {
        if (profilePhoto == null || profilePhoto.isEmpty()) {
            return;
        }

        try {
            seller.setProfilePhotoData(profilePhoto.getBytes());
            seller.setProfilePhotoContentType(profilePhoto.getContentType());
            seller.setProfilePhotoUrl(null);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to store seller profile photo.", ex);
        }
    }

    private void prepareSellerForSave(Seller seller) {
        if (seller.getBusinessName() == null || seller.getBusinessName().isBlank()) {
            throw new RuntimeException("Business name is required.");
        }
        if (seller.getPassword() == null || seller.getPassword().isBlank()) {
            throw new RuntimeException("Password is required.");
        }

        String normalizedEmail = normalizeEmail(seller.getEmail());
        if (normalizedEmail.isBlank()) {
            throw new RuntimeException("Email is required.");
        }
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new RuntimeException("A user with that email already exists.");
        }

        seller.setEmail(normalizedEmail);
        seller.setPassword(seller.getPassword().trim());
        seller.setBusinessName(seller.getBusinessName().trim());
    }

    private Seller saveSellerWithPhotoFallback(Seller seller, MultipartFile profilePhoto) {
        try {
            return persistAndForceSellerType(seller);
        } catch (DataAccessException ex) {
            if (profilePhoto != null && !profilePhoto.isEmpty()) {
                seller.setProfilePhotoData(null);
                seller.setProfilePhotoContentType(null);
                return persistAndForceSellerType(seller);
            }
            throw ex;
        }
    }

    private Seller persistAndForceSellerType(Seller seller) {
        Seller savedSeller = sellerRepository.saveAndFlush(seller);
        sellerRepository.forceSellerUserType(savedSeller.getUserId());
        return savedSeller;
    }

    public boolean credentialsMatch(Seller seller, String password) {
        if (seller == null || seller.getPassword() == null || password == null) {
            return false;
        }

        return seller.getPassword().equals(password)
                || seller.getPassword().trim().equals(password.trim());
    }

    private String normalizeEmail(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }
}
