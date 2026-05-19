package com.skinmarket.model;

import java.time.LocalDateTime;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private String previewImage;
    private Integer authorId;
    private String status;
    private int viewsCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Article() {}


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

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getPreviewImage() {
        return previewImage;
    }
    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public Integer getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getViewsCount() {
        return viewsCount;
    }
    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
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