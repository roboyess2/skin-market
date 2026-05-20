package com.skinmarket.dao;

import com.skinmarket.model.Transaction;
import java.util.List;

public interface TransactionDao {
    List<Transaction> findByBuyer(int buyerId);
    List<Transaction> findBySeller(int sellerId);
    void save(Transaction transaction);
}