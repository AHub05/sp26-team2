package com.CSC340.MinervasList.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false, length = 1000)
    private String comment;

    // Who wrote the review
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Who the review is about
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    public Review() {
    }

    public Review(int rating, String comment, Customer customer, Seller seller) {
        this.rating = rating;
        this.comment = comment;
        this.customer = customer;
        this.seller = seller;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}

