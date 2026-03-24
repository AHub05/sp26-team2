package com.CSC340.MinervasList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.entity.User;
import com.CSC340.MinervasList.repository.SellerRepository;
import com.CSC340.MinervasList.repository.UserRepository;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;

    public SellerService(SellerRepository sellerRepository, UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));
    }

    public Seller getSellerByUserId(Long userId) {
        return sellerRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Seller not found for user ID: " + userId));
    }

    public List<Seller> searchByBusinessName(String businessName) {
        return sellerRepository.findByBusinessNameContainingIgnoreCase(businessName);
    }

    public Seller createSeller(Seller seller) {
        if (seller.getUser() == null || seller.getUser().getUserId() == null) {
            throw new RuntimeException("Seller must be linked to an existing user.");
        }

        Long userId = seller.getUser().getUserId();

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        if (sellerRepository.existsByUserUserId(userId)) {
            throw new RuntimeException("This user already has a seller profile.");
        }

        seller.setUser(existingUser);

        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long sellerId, Seller updatedSeller) {
        Seller existingSeller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        existingSeller.setBusinessName(updatedSeller.getBusinessName());

        if (updatedSeller.getUser() != null && updatedSeller.getUser().getUserId() != null) {
            Long userId = updatedSeller.getUser().getUserId();

            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

            if (!existingSeller.getUser().getUserId().equals(userId)
                    && sellerRepository.existsByUserUserId(userId)) {
                throw new RuntimeException("This user already has a seller profile.");
            }

            existingSeller.setUser(existingUser);
        }

        return sellerRepository.save(existingSeller);
    }

    public void deleteSeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        sellerRepository.delete(seller);
    }
}
