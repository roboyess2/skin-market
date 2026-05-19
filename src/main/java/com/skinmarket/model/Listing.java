package com.skinmarket.model;

import java.time.LocalDateTime;

public class Listing {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String sellerType; // USER или SITE
    private String status; // ACTIVE, FROZEN, SOLD, DELETED_BY_USER, DELETED_BY_MODERATOR
    private Integer skinId;
    private Integer sellerId;
    private Integer addedByModeratorId;
    private Double floatValue;
    private Integer patternIndex;
    private String stickerInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Listing() {}


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSellerType() {
        return sellerType;
    }
    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSkinId() {
        return skinId;
    }
    public void setSkinId(Integer skinId) {
        this.skinId = skinId;
    }

    public Integer getSellerId() {
        return sellerId;
    }
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getAddedByModeratorId() {
        return addedByModeratorId;
    }
    public void setAddedByModeratorId(Integer addedByModeratorId) {
        this.addedByModeratorId = addedByModeratorId;
    }

    public Double getFloatValue() {
        return floatValue;
    }
    public void setFloatValue(Double floatValue) {
        this.floatValue = floatValue;
    }

    public Integer getPatternIndex() {
        return patternIndex;
    }
    public void setPatternIndex(Integer patternIndex) {
        this.patternIndex = patternIndex;
    }

    public String getStickerInfo() {
        return stickerInfo;
    }
    public void setStickerInfo(String stickerInfo) {
        this.stickerInfo = stickerInfo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}