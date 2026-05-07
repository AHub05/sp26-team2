package com.CSC340.MinervasList.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.repository.ListingRepository;
import com.CSC340.MinervasList.repository.PurchaseRepository;
import com.CSC340.MinervasList.repository.SellerRepository;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final SellerRepository sellerRepository;
    private final PurchaseRepository purchaseRepository;

    public ListingService(ListingRepository listingRepository, SellerRepository sellerRepository,
                          PurchaseRepository purchaseRepository) {
        this.listingRepository = listingRepository;
        this.sellerRepository = sellerRepository;
        this.purchaseRepository = purchaseRepository;
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

    @Transactional
    public Listing createListingForSellerWithPhoto(Long sellerId, Listing listing, MultipartFile listingPhoto) {
        applyListingPhoto(listing, listingPhoto);
        return createListingForSeller(sellerId, listing);
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

    @Transactional
    public Listing updateSellerListingWithPhoto(Long sellerId, Long listingId, Listing updatedListing,
                                                MultipartFile listingPhoto) {
        Listing existing = getSellerListing(sellerId, listingId);
        copyEditableFields(existing, updatedListing);
        applyListingPhoto(existing, listingPhoto);
        return listingRepository.save(existing);
    }

    public void deleteSellerListing(Long sellerId, Long listingId) {
        Listing listing = getSellerListing(sellerId, listingId);

        // Prevent deleting listings that have purchases associated.
        if (!purchaseRepository.findByListingListingId(listingId).isEmpty()) {
            throw new RuntimeException("Cannot delete listing with existing purchases");
        }

        listingRepository.delete(listing);
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

    private void applyListingPhoto(Listing listing, MultipartFile listingPhoto) {
        if (listingPhoto == null || listingPhoto.isEmpty()) {
            return;
        }

        try {
            listing.setPhotoData(listingPhoto.getBytes());
            listing.setPhotoContentType(listingPhoto.getContentType());
        } catch (IOException ex) {
            throw new RuntimeException("Unable to store listing photo.", ex);
        }
    }
}
