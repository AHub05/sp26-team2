package com.CSC340.MinervasList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CSC340.MinervasList.entity.Seller;
import com.CSC340.MinervasList.service.SellerService;

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
        return sellerService.getSellerById(userId);
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