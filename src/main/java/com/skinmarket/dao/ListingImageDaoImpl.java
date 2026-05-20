package com.skinmarket.dao;

import com.skinmarket.model.ListingImage;
import com.skinmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingImageDaoImpl implements ListingImageDao {

    @Override
    public List<ListingImage> findByListingId(int listingId) {
        List<ListingImage> list = new ArrayList<>();
        String sql = "SELECT id, listing_id, image_path, is_primary, uploaded_at FROM listing_images WHERE listing_id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, listingId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapImage(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void save(ListingImage image) {
        String sql = "INSERT INTO listing_images (listing_id, image_path, is_primary) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, image.getListingId());
            ps.setString(2, image.getImagePath());
            ps.setBoolean(3, image.isPrimary());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                image.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByListingId(int listingId) {
        String sql = "DELETE FROM listing_images WHERE listing_id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, listingId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ListingImage mapImage(ResultSet rs) throws SQLException {
        ListingImage img = new ListingImage();
        img.setId(rs.getInt("id"));
        img.setListingId(rs.getInt("listing_id"));
        img.setImagePath(rs.getString("image_path"));
        img.setPrimary(rs.getBoolean("is_primary"));
        img.setUploadedAt(rs.getTimestamp("uploaded_at").toLocalDateTime());
        return img;
    }
}