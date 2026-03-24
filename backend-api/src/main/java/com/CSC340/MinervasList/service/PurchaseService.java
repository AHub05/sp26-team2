package com.CSC340.MinervasList.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.CSC340.MinervasList.entity.Customer;
import com.CSC340.MinervasList.entity.Listing;
import com.CSC340.MinervasList.entity.Purchase;
import com.CSC340.MinervasList.repository.CustomerRepository;
import com.CSC340.MinervasList.repository.ListingRepository;
import com.CSC340.MinervasList.repository.PurchaseRepository;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final ListingRepository listingRepository;

    public PurchaseService(PurchaseRepository purchaseRepository,
                           CustomerRepository customerRepository,
                           ListingRepository listingRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.listingRepository = listingRepository;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found with ID: " + purchaseId));
    }

    public List<Purchase> getPurchasesByCustomerId(Long customerId) {
        return purchaseRepository.findByCustomerCustomerId(customerId);
    }

    public List<Purchase> getPurchasesByListingId(Long listingId) {
        return purchaseRepository.findByListingListingId(listingId);
    }

    public Purchase createPurchase(Purchase purchase) {
        if (purchase.getCustomer() == null || purchase.getCustomer().getCustomerId() == null) {
            throw new RuntimeException("Purchase must be linked to an existing customer.");
        }

        if (purchase.getListing() == null || purchase.getListing().getListingId() == null) {
            throw new RuntimeException("Purchase must be linked to an existing listing.");
        }

        if (purchase.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0.");
        }

        Long customerId = purchase.getCustomer().getCustomerId();
        Long listingId = purchase.getListing().getListingId();

        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        Listing existingListing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found with ID: " + listingId));

        purchase.setCustomer(existingCustomer);
        purchase.setListing(existingListing);
        purchase.setPurchaseDate(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(Long purchaseId, Purchase updatedPurchase) {
        Purchase existingPurchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found with ID: " + purchaseId));

        if (updatedPurchase.getQuantity() > 0) {
            existingPurchase.setQuantity(updatedPurchase.getQuantity());
        }

        existingPurchase.setTotalPrice(updatedPurchase.getTotalPrice());

        if (updatedPurchase.getCustomer() != null && updatedPurchase.getCustomer().getCustomerId() != null) {
            Long customerId = updatedPurchase.getCustomer().getCustomerId();

            Customer existingCustomer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

            existingPurchase.setCustomer(existingCustomer);
        }

        if (updatedPurchase.getListing() != null && updatedPurchase.getListing().getListingId() != null) {
            Long listingId = updatedPurchase.getListing().getListingId();

            Listing existingListing = listingRepository.findById(listingId)
                    .orElseThrow(() -> new RuntimeException("Listing not found with ID: " + listingId));

            existingPurchase.setListing(existingListing);
        }

        return purchaseRepository.save(existingPurchase);
    }

    public void deletePurchase(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found with ID: " + purchaseId));

        purchaseRepository.delete(purchase);
    }
}