package com.CSC340.MinervasList.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    // Who bought it
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // What was purchased
    @ManyToOne
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;

    public Purchase() {
        this.purchaseDate = LocalDateTime.now();
    }

    public Purchase(int quantity, double totalPrice, Customer customer, Listing listing) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.listing = listing;
        this.purchaseDate = LocalDateTime.now();
    }

    public Long getId() {
        return purchaseId;
    }

    public void setId(Long id) {
        this.purchaseId = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}

