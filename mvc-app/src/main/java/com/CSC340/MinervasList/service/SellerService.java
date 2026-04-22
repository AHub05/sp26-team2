package com.CSC340.MinervasList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.repository.SellerRepository;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
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

        return sellerRepository.save(existingSeller);
    }

    public void deleteSeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        sellerRepository.delete(seller);
    }
}