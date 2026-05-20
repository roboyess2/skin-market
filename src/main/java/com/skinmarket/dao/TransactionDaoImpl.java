package com.skinmarket.dao;

import com.skinmarket.model.Transaction;
import com.skinmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public List<Transaction> findByBuyer(int buyerId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT id, listing_id, buyer_id, seller_id, amount, transaction_type, status, created_at FROM transactions WHERE buyer_id=? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapTransaction(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Transaction> findBySeller(int sellerId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT id, listing_id, buyer_id, seller_id, amount, transaction_type, status, created_at FROM transactions WHERE seller_id=? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sellerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapTransaction(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (listing_id, buyer_id, seller_id, amount, transaction_type, status) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, transaction.getListingId());
            ps.setInt(2, transaction.getBuyerId());
            if (transaction.getSellerId() != null)
                ps.setInt(3, transaction.getSellerId());
            else
                ps.setNull(3, Types.INTEGER);
            ps.setDouble(4, transaction.getAmount());
            ps.setString(5, transaction.getTransactionType());
            ps.setString(6, transaction.getStatus());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                transaction.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Transaction mapTransaction(ResultSet rs) throws SQLException {
        Transaction tx = new Transaction();
        tx.setId(rs.getInt("id"));
        tx.setListingId(rs.getInt("listing_id"));
        tx.setBuyerId(rs.getInt("buyer_id"));
        int sellerId = rs.getInt("seller_id");
        if (!rs.wasNull()) tx.setSellerId(sellerId);
        tx.setAmount(rs.getDouble("amount"));
        tx.setTransactionType(rs.getString("transaction_type"));
        tx.setStatus(rs.getString("status"));
        tx.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return tx;
    }
}