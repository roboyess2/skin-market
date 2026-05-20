package com.skinmarket.dao;

import com.skinmarket.model.Listing;
import com.skinmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingDaoImpl implements ListingDao {

    @Override
    public List<Listing> findAllActiveListing() {
        List<Listing> list = new ArrayList<>();
        String sql = "SELECT id, title, description, price, seller_type, status, skin_id, seller_id, added_by_moderator_id, float_value, pattern_index, sticker_info, created_at, updated_at FROM listings WHERE status='ACTIVE' ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapListing(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Listing> findListingBySeller(int sellerId) {
        List<Listing> list = new ArrayList<>();
        String sql = "SELECT id, title, description, price, seller_type, status, skin_id, seller_id, added_by_moderator_id, float_value, pattern_index, sticker_info, created_at, updated_at FROM listings WHERE seller_id=? AND seller_type='USER' ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sellerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapListing(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Listing> findListingByModerator(int moderatorId) {
        List<Listing> list = new ArrayList<>();
        String sql = "SELECT id, title, description, price, seller_type, status, skin_id, seller_id, added_by_moderator_id, float_value, pattern_index, sticker_info, created_at, updated_at FROM listings WHERE added_by_moderator_id=? AND seller_type='SITE' ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, moderatorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapListing(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Listing findById(int id) {
        String sql = "SELECT id, title, description, price, seller_type, status, skin_id, seller_id, added_by_moderator_id, float_value, pattern_index, sticker_info, created_at, updated_at FROM listings WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapListing(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(Listing listing) {
        String sql = "INSERT INTO listings (title, description, price, seller_type, status, skin_id, seller_id, added_by_moderator_id, float_value, pattern_index, sticker_info) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, listing.getTitle());
            ps.setString(2, listing.getDescription());
            ps.setDouble(3, listing.getPrice());
            ps.setString(4, listing.getSellerType());
            ps.setString(5, listing.getStatus());
            if (listing.getSkinId() != null)
                ps.setInt(6, listing.getSkinId());
            else
                ps.setNull(6, Types.INTEGER);
            if (listing.getSellerId() != null)
                ps.setInt(7, listing.getSellerId());
            else
                ps.setNull(7, Types.INTEGER);
            if (listing.getAddedByModeratorId() != null)
                ps.setInt(8, listing.getAddedByModeratorId());
            else
                ps.setNull(8, Types.INTEGER);
            if (listing.getFloatValue() != null)
                ps.setDouble(9, listing.getFloatValue());
            else
                ps.setNull(9, Types.DOUBLE);
            if (listing.getPatternIndex() != null)
                ps.setInt(10, listing.getPatternIndex());
            else
                ps.setNull(10, Types.INTEGER);
            ps.setString(11, listing.getStickerInfo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                listing.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatus(int listingId, String status) {
        String sql = "UPDATE listings SET status=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, listingId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Listing listing) {
        String sql = "UPDATE listings SET title=?, description=?, price=?, skin_id=?, float_value=?, pattern_index=?, sticker_info=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, listing.getTitle());
            ps.setString(2, listing.getDescription());
            ps.setDouble(3, listing.getPrice());
            if (listing.getSkinId() != null)
                ps.setInt(4, listing.getSkinId());
            else
                ps.setNull(4, Types.INTEGER);
            if (listing.getFloatValue() != null)
                ps.setDouble(5, listing.getFloatValue());
            else
                ps.setNull(5, Types.DOUBLE);
            if (listing.getPatternIndex() != null)
                ps.setInt(6, listing.getPatternIndex());
            else
                ps.setNull(6, Types.INTEGER);
            ps.setString(7, listing.getStickerInfo());
            ps.setInt(8, listing.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int listingId) {
        String sql = "DELETE FROM listings WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, listingId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Listing mapListing(ResultSet rs) throws SQLException {
        Listing listing = new Listing();
        listing.setId(rs.getInt("id"));
        listing.setTitle(rs.getString("title"));
        listing.setDescription(rs.getString("description"));
        listing.setPrice(rs.getDouble("price"));
        listing.setSellerType(rs.getString("seller_type"));
        listing.setStatus(rs.getString("status"));
        int skinId = rs.getInt("skin_id");
        if (!rs.wasNull()) listing.setSkinId(skinId);
        int sellerId = rs.getInt("seller_id");
        if (!rs.wasNull()) listing.setSellerId(sellerId);
        int modId = rs.getInt("added_by_moderator_id");
        if (!rs.wasNull()) listing.setAddedByModeratorId(modId);
        double fv = rs.getDouble("float_value");
        if (!rs.wasNull()) listing.setFloatValue(fv);
        int pi = rs.getInt("pattern_index");
        if (!rs.wasNull()) listing.setPatternIndex(pi);
        listing.setStickerInfo(rs.getString("sticker_info"));
        listing.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        listing.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return listing;
    }
}