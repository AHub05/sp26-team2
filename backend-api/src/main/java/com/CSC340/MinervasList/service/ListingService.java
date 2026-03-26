package com.CSC340.MinervasList.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.repository.ListingRepository;

@Service
public class ListingService {
     private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    // Get all listings
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    // Get listing by ID
    public Listing getListingById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
    }

    // Create listing
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    // Update listing
    public Listing updateListing(Long id, Listing updatedListing) {
        Listing existing = getListingById(id);

        existing.setTitle(updatedListing.getTitle());
        existing.setDescription(updatedListing.getDescription());
        existing.setCategory(updatedListing.getCategory());
        existing.setItemCondition(updatedListing.getItemCondition());
        existing.setPrice(updatedListing.getPrice());
        existing.setQuantity(updatedListing.getQuantity());
        existing.setStatus(updatedListing.getStatus());

        return listingRepository.save(existing);
    }

    // Delete listing
    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }
}
