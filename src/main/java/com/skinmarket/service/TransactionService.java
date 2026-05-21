package com.skinmarket.service;

import com.skinmarket.dao.TransactionDao;
import com.skinmarket.dao.TransactionDaoImpl;
import com.skinmarket.model.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionDao transactionDao;

    public TransactionService() {
        this.transactionDao = new TransactionDaoImpl();
    }

    public List<Transaction> getBuyerTransactions(int buyerId) {
        return transactionDao.findByBuyer(buyerId);
    }

    public List<Transaction> getSellerTransactions(int sellerId) {
        return transactionDao.findBySeller(sellerId);
    }

    public void createTransaction(Transaction transaction) {
        transactionDao.save(transaction);
    }
}