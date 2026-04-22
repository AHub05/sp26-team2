package com.CSC340.MinervasList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.repository.ListingRepository;
import com.CSC340.MinervasList.repository.SellerRepository;

@RestController
@RequestMapping("/listings")
public class ListingController {

    private final ListingRepository listingRepository;
    private final SellerRepository sellerRepository;

    public ListingController(ListingRepository listingRepository, SellerRepository sellerRepository) {
        this.listingRepository = listingRepository;
        this.sellerRepository = sellerRepository;
    }


    @GetMapping
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

   @PostMapping("/seller/{sellerId}")
public Listing createListing(@PathVariable Long sellerId, @RequestBody Listing listing) {
    Seller seller = sellerRepository.findById(sellerId).orElse(null);

    if (seller == null) {
        return null;
    }

    listing.setSeller(seller);
    return listingRepository.save(listing);
}
}
