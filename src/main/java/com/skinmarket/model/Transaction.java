package com.skinmarket.model;

import java.time.LocalDateTime;

public class Transaction {
    private Integer id;
    private Integer listingId;
    private Integer buyerId;
    private Integer sellerId;
    private Double amount;
    private String transactionType; // PURCHASE, REFUND, PAYOUT
    private String status; // PENDING, COMPLETED, CANCELLED
    private LocalDateTime createdAt;

    public Transaction() {}


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListingId() {
        return listingId;
    }
    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}