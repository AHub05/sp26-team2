package com.CSC340.MinervasList.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.service.ListingService;

@RestController
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("/{listingId}")
    public Listing getListingById(@PathVariable Long listingId) {
        return listingService.getListingById(listingId);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Listing> getListingsBySeller(@PathVariable Long sellerId) {
        return listingService.getListingsBySellerId(sellerId);
    }

    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<Listing> createListing(@PathVariable Long sellerId, @RequestBody Listing listing) {
        try {
            Listing createdListing = listingService.createListingForSeller(sellerId, listing);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdListing);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PutMapping("/{listingId}/seller/{sellerId}")
    public Listing updateSellerListing(@PathVariable Long listingId,
                                       @PathVariable Long sellerId,
                                       @RequestBody Listing listing) {
        try {
            return listingService.updateSellerListing(sellerId, listingId, listing);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
