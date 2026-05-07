package com.CSC340.MinervasList.entity;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("SELLER")
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class Seller extends User {

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @Column(name = "profile_photo_data", columnDefinition = "bytea")
    private byte[] profilePhotoData;

    @Column(name = "profile_photo_content_type")
    private String profilePhotoContentType;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "services_offered", columnDefinition = "TEXT")
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

    public byte[] getProfilePhotoData() {
        return profilePhotoData;
    }

    public void setProfilePhotoData(byte[] profilePhotoData) {
        this.profilePhotoData = profilePhotoData;
    }

    public String getProfilePhotoContentType() {
        return profilePhotoContentType;
    }

    public void setProfilePhotoContentType(String profilePhotoContentType) {
        this.profilePhotoContentType = profilePhotoContentType;
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

    public boolean hasStoredProfilePhoto() {
        return profilePhotoData != null && profilePhotoData.length > 0;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
}
