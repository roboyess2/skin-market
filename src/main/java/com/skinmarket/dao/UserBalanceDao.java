package com.skinmarket.dao;

import com.skinmarket.model.UserBalance;
import java.util.Optional;

public interface UserBalanceDao {
    UserBalance findByUserId(int userId);
    void save(UserBalance balance);   // вставка нового
    void updateBalance(int userId, double newBalance);
}