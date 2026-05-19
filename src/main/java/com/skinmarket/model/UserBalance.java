package com.skinmarket.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserBalance {
    private Integer id;
    private Integer userId;
    private double balance;
    private LocalDateTime updatedAt;

    public UserBalance() {}


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getUpdatedAt()
    { return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}