package com.skinmarket.dao;

import com.skinmarket.model.Skin;
import com.skinmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkinDaoImpl implements SkinDao {

    @Override
    public List<Skin> findAll() {
        List<Skin> list = new ArrayList<>();
        String sql = "SELECT id, name, weapon, rarity, exterior, collection_name, description, image_path, is_stattrak, is_souvenir, created_at FROM skins ORDER BY name";
        try (Connection conn = DBConnection.DbConnect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapSkin(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Skin findById(int id) {
        String sql = "SELECT id, name, weapon, rarity, exterior, collection_name, description, image_path, is_stattrak, is_souvenir, created_at FROM skins WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapSkin(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(Skin skin) {
        String sql = "INSERT INTO skins (name, weapon, rarity, exterior, collection_name, description, image_path, is_stattrak, is_souvenir) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, skin.getName());
            ps.setString(2, skin.getWeapon());
            ps.setString(3, skin.getRarity());
            ps.setString(4, skin.getExterior());
            ps.setString(5, skin.getCollectionName());
            ps.setString(6, skin.getDescription());
            ps.setString(7, skin.getImagePath());
            ps.setBoolean(8, skin.isStattrak());
            ps.setBoolean(9, skin.isSouvenir());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                skin.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Skin mapSkin(ResultSet rs) throws SQLException {
        Skin skin = new Skin();
        skin.setId(rs.getInt("id"));
        skin.setName(rs.getString("name"));
        skin.setWeapon(rs.getString("weapon"));
        skin.setRarity(rs.getString("rarity"));
        skin.setExterior(rs.getString("exterior"));
        skin.setCollectionName(rs.getString("collection_name"));
        skin.setDescription(rs.getString("description"));
        skin.setImagePath(rs.getString("image_path"));
        skin.setStattrak(rs.getBoolean("is_stattrak"));
        skin.setSouvenir(rs.getBoolean("is_souvenir"));
        skin.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return skin;
    }
}