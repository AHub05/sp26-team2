package com.CSC340.MinervasList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sellers")
public class Seller extends User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long sellerId;

    @Column(nullable = false)
    private String businessName;

    public Seller() {
    }

    public Seller(String businessName, String email, String password) {
        super(email, password);
        this.businessName = businessName;
    }

    public Seller(Long id, String businessName, String email, String password) {
        super(email, password);
        this.sellerId = id;
        this.businessName = businessName;
    }

    public Long getId() {
        return sellerId;
    }

    public void setId(Long id) {
        this.sellerId = id;
    }

    public String getBusiness() {
        return businessName;
    }

    public void getBusiness(String businessName) {
        this.businessName = businessName;
    }
}
