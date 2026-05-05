package com.CSC340.MinervasList.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("SELLER")
@EqualsAndHashCode(callSuper = true)
public class Seller extends User {

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @Lob
    @Column(name = "bio")
    private String bio;

    @Lob
    @Column(name = "services_offered")
    private String servicesOffered;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("seller")
    private List<Listing> listings;

    public Seller() {
    }

    public Seller(String email, String password, String businessName) {
        super(email, password);
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getServicesOffered() {
        return servicesOffered;
    }

    public void setServicesOffered(String servicesOffered) {
        this.servicesOffered = servicesOffered;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
}
