package com.CSC340.MinervasList.controller;

import com.CSC340.MinervasList.entity.Purchase;
import com.CSC340.MinervasList.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{purchaseId}")
    public Purchase getPurchaseById(@PathVariable Long purchaseId) {
        return purchaseService.getPurchaseById(purchaseId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Purchase> getPurchasesByCustomerId(@PathVariable Long customerId) {
        return purchaseService.getPurchasesByCustomerId(customerId);
    }

    @GetMapping("/listing/{listingId}")
    public List<Purchase> getPurchasesByListingId(@PathVariable Long listingId) {
        return purchaseService.getPurchasesByListingId(listingId);
    }

    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

    @PutMapping("/{purchaseId}")
    public Purchase updatePurchase(@PathVariable Long purchaseId, @RequestBody Purchase updatedPurchase) {
        return purchaseService.updatePurchase(purchaseId, updatedPurchase);
    }

    @DeleteMapping("/{purchaseId}")
    public String deletePurchase(@PathVariable Long purchaseId) {
        purchaseService.deletePurchase(purchaseId);
        return "Purchase deleted successfully.";
    }
}