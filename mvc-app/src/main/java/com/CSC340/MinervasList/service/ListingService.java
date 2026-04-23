package com.CSC340.MinervasList.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.repository.ListingRepository;
import com.CSC340.MinervasList.repository.SellerRepository;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final SellerRepository sellerRepository;

    public ListingService(ListingRepository listingRepository, SellerRepository sellerRepository) {
        this.listingRepository = listingRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Listing getListingById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found with ID: " + id));
    }

    public List<Listing> getListingsBySellerId(Long sellerId) {
        sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        return listingRepository.findBySellerId(sellerId);
    }

    public Listing getSellerListing(Long sellerId, Long listingId) {
        Listing listing = getListingById(listingId);

        if (listing.getSeller() == null || !Objects.equals(listing.getSeller().getUserId(), sellerId)) {
            throw new RuntimeException("Listing does not belong to seller with ID: " + sellerId);
        }

        return listing;
    }

    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public Listing createListingForSeller(Long sellerId, Listing listing) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + sellerId));

        listing.setSeller(seller);
        return listingRepository.save(listing);
    }

    public Listing updateListing(Long id, Listing updatedListing) {
        Listing existing = getListingById(id);
        copyEditableFields(existing, updatedListing);
        return listingRepository.save(existing);
    }

    public Listing updateSellerListing(Long sellerId, Long listingId, Listing updatedListing) {
        Listing existing = getSellerListing(sellerId, listingId);
        copyEditableFields(existing, updatedListing);
        return listingRepository.save(existing);
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }

    private void copyEditableFields(Listing existing, Listing updatedListing) {
        existing.setTitle(updatedListing.getTitle());
        existing.setDescription(updatedListing.getDescription());
        existing.setCategory(updatedListing.getCategory());
        existing.setItemCondition(updatedListing.getItemCondition());
        existing.setPrice(updatedListing.getPrice());
        existing.setQuantity(updatedListing.getQuantity());
        existing.setStatus(updatedListing.getStatus());
    }
}
