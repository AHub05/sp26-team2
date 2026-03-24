package com.CSC340.MinervasList.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "listings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @JsonIgnoreProperties("listings")
    private Seller seller;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemCondition itemCondition;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ListingStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = ListingStatus.AVAILABLE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum ListingStatus {
        AVAILABLE, PENDING, SOLD, ARCHIVED
    }

    public enum Category {
        ELECTRONICS, FURNITURE, BOOKS, CLOTHING, SCHOOL_SUPPLIES, SERVICES, OTHER
    }

    public enum ItemCondition {
        NEW, LIKE_NEW, GOOD, FAIR, POOR
    }
}
