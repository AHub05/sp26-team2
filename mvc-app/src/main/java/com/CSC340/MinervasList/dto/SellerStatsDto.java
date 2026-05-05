package com.CSC340.MinervasList.dto;

public class SellerStatsDto {

    private Long sellerId;
    private long totalListings;
    private long activeListings;
    private long completedTransactions;
    private long totalReviews;
    private double averageRating;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public long getTotalListings() {
        return totalListings;
    }

    public void setTotalListings(long totalListings) {
        this.totalListings = totalListings;
    }

    public long getActiveListings() {
        return activeListings;
    }

    public void setActiveListings(long activeListings) {
        this.activeListings = activeListings;
    }

    public long getCompletedTransactions() {
        return completedTransactions;
    }

    public void setCompletedTransactions(long completedTransactions) {
        this.completedTransactions = completedTransactions;
    }

    public long getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(long totalReviews) {
        this.totalReviews = totalReviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
