package com.CSC340.MinervasList.controller;

import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.service.SellerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
@CrossOrigin(origins = "*")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{sellerId}")
    public Seller getSellerById(@PathVariable Long sellerId) {
        return sellerService.getSellerById(sellerId);
    }

    @GetMapping("/user/{userId}")
    public Seller getSellerByUserId(@PathVariable Long userId) {
        return sellerService.getSellerByUserId(userId);
    }

    @GetMapping("/search")
    public List<Seller> searchSellersByBusinessName(@RequestParam String businessName) {
        return sellerService.searchByBusinessName(businessName);
    }

    @PostMapping
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerService.createSeller(seller);
    }

    @PutMapping("/{sellerId}")
    public Seller updateSeller(@PathVariable Long sellerId, @RequestBody Seller updatedSeller) {
        return sellerService.updateSeller(sellerId, updatedSeller);
    }

    @DeleteMapping("/{sellerId}")
    public String deleteSeller(@PathVariable Long sellerId) {
        sellerService.deleteSeller(sellerId);
        return "Seller deleted successfully.";
    }
}