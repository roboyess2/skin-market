package com.skinmarket.model;

import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String userName;
    private String passwordHash;
    private String email;
    private String role;
    private String avatarPath;
    private String steamId;
    private String tradeLink;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String userName, String passwordHash, String email){
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = "User";
        this.isActive = true;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarPath(){
        return avatarPath;
    }
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getSteamId() {
        return steamId;
    }
    public void setSteamId(String steamId){
        this.steamId = steamId;
    }

    public String getTradeLink() {
        return tradeLink;
    }
    public void setTradeLink(String tradeLink) {
        this.tradeLink = tradeLink;
    }

    public boolean getIsActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
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
