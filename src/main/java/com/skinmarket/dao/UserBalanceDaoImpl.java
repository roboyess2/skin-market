package com.skinmarket.dao;

import com.skinmarket.model.UserBalance;
import com.skinmarket.util.DBConnection;

import java.sql.*;
import java.util.Optional;

public class UserBalanceDaoImpl implements UserBalanceDao {

    @Override
    public UserBalance findByUserId(int userId) {
        String sql = "SELECT id, user_id, balance, updated_at FROM user_balances WHERE user_id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapBalance(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(UserBalance balance) {
        String sql = "INSERT INTO user_balances (user_id, balance) VALUES (?, ?) RETURNING id";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, balance.getUserId());
            ps.setDouble(2, balance.getBalance());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBalance(int userId, double newBalance) {
        String sql = "UPDATE user_balances SET balance=?, updated_at=CURRENT_TIMESTAMP WHERE user_id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserBalance mapBalance(ResultSet rs) throws SQLException {
        UserBalance ub = new UserBalance();
        ub.setId(rs.getInt("id"));
        ub.setUserId(rs.getInt("user_id"));
        ub.setBalance(rs.getDouble("balance"));
        ub.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return ub;
    }
}