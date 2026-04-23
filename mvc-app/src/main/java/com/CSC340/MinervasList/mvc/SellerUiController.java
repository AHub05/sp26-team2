package com.CSC340.MinervasList.mvc;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.service.ListingService;
import com.CSC340.MinervasList.service.SellerService;

@Controller
@RequestMapping("/seller")
public class SellerUiController {

    private final ListingService listingService;
    private final SellerService sellerService;

    public SellerUiController(ListingService listingService, SellerService sellerService) {
        this.listingService = listingService;
        this.sellerService = sellerService;
    }

    @GetMapping("/listings")
    public String sellerListings(@RequestParam(required = false) Long sellerId, Model model) {
        List<Seller> sellers = sellerService.getAllSellers();
        Seller selectedSeller = null;
        List<Listing> listings = Collections.emptyList();

        if (sellerId != null) {
            selectedSeller = sellerService.getSellerById(sellerId);
            listings = listingService.getListingsBySellerId(sellerId);
        }

        model.addAttribute("sellers", sellers);
        model.addAttribute("selectedSeller", selectedSeller);
        model.addAttribute("selectedSellerId", sellerId);
        model.addAttribute("listings", listings);
        model.addAttribute("demoSeller", new Seller());
        return "seller-listings";
    }

    @GetMapping("/listings/new")
    public String newListingForm(@RequestParam Long sellerId, Model model) {
        addListingFormAttributes(model, sellerId, new Listing(), "Create Listing", "/seller/listings?sellerId=" + sellerId);
        return "seller-listing-form";
    }

    @PostMapping("/listings")
    public String createListing(@RequestParam Long sellerId, @ModelAttribute Listing listing) {
        listingService.createListingForSeller(sellerId, listing);
        return "redirect:/seller/listings?sellerId=" + sellerId;
    }

    @GetMapping("/listings/{listingId}/edit")
    public String editListingForm(@PathVariable Long listingId, @RequestParam Long sellerId, Model model) {
        Listing listing = listingService.getSellerListing(sellerId, listingId);
        addListingFormAttributes(model, sellerId, listing, "Update Listing",
                "/seller/listings/" + listingId + "?sellerId=" + sellerId);
        return "seller-listing-form";
    }

    @PostMapping("/listings/{listingId}")
    public String updateListing(@PathVariable Long listingId,
                                @RequestParam Long sellerId,
                                @ModelAttribute Listing listing) {
        listingService.updateSellerListing(sellerId, listingId, listing);
        return "redirect:/seller/listings?sellerId=" + sellerId;
    }

    @PostMapping("/demo-seller")
    public String createDemoSeller(@ModelAttribute Seller demoSeller) {
        Seller createdSeller = sellerService.createSeller(demoSeller);
        return "redirect:/seller/listings?sellerId=" + createdSeller.getUserId();
    }

    private void addListingFormAttributes(Model model, Long sellerId, Listing listing, String formTitle, String formAction) {
        Seller seller = sellerService.getSellerById(sellerId);
        model.addAttribute("seller", seller);
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("listing", listing);
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("formAction", formAction);
        model.addAttribute("categories", Listing.Category.values());
        model.addAttribute("conditions", Listing.ItemCondition.values());
        model.addAttribute("statuses", Listing.ListingStatus.values());
    }
}
