package com.skinmarket.model;

import java.time.LocalDateTime;

public class ListingImage {
    private Integer id;
    private Integer listingId;
    private String imagePath;
    private boolean isPrimary;
    private LocalDateTime uploadedAt;

    public ListingImage() {}


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

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isPrimary() {
        return isPrimary;
    }
    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}