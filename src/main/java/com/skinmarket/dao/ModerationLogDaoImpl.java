package com.skinmarket.dao;

import com.skinmarket.model.ModerationLog;
import com.skinmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModerationLogDaoImpl implements ModerationLogDao {

    @Override
    public List<ModerationLog> findByModerator(int moderatorId){
        List<ModerationLog> list = new ArrayList<>();
        String sql = "SELECT id, moderator_id, listing_id, article_id, action, reason, created_at FROM moderation_logs WHERE moderator_id=? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, moderatorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapLog(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void save(ModerationLog log) {
        String sql = "INSERT INTO moderation_logs (moderator_id, listing_id, article_id, action, reason) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, log.getModeratorId());
            if (log.getListingId() != null)
                ps.setInt(2, log.getListingId());
            else
                ps.setNull(2, Types.INTEGER);
            if (log.getArticleId() != null)
                ps.setInt(3, log.getArticleId());
            else
                ps.setNull(3, Types.INTEGER);
            ps.setString(4, log.getAction());
            ps.setString(5, log.getReason());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                log.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ModerationLog mapLog(ResultSet rs) throws SQLException{
        ModerationLog log = new ModerationLog();
        log.setId(rs.getInt("id"));
        log.setModeratorId(rs.getInt("moderator_id"));
        int listingId = rs.getInt("listing_id");
        if (!rs.wasNull()) log.setListingId(listingId);
        int articleId = rs.getInt("article_id");
        if (!rs.wasNull()) log.setArticleId(articleId);
        log.setAction(rs.getString("action"));
        log.setReason(rs.getString("reason"));
        log.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return log;
    }
}
