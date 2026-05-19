package com.skinmarket.model;

import java.time.LocalDateTime;

public class ModerationLog {
    private Integer id;
    private Integer moderatorId;
    private Integer listingId;
    private Integer articleId;
    private String action;
    private String reason;
    private LocalDateTime createdAt;

    public ModerationLog() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModeratorId() {
        return moderatorId;
    }
    public void setModeratorId(Integer moderatorId) {
        this.moderatorId = moderatorId;
    }

    public Integer getListingId() {
        return listingId;
    }
    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public Integer getArticleId() {
        return articleId;
    }
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}