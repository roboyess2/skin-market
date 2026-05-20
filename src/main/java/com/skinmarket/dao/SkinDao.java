package com.skinmarket.dao;

import com.skinmarket.model.Skin;
import java.util.List;
import java.util.Optional;

public interface SkinDao {
    List<Skin> findAll();
    Skin findById(int id);
    void save(Skin skin);
}