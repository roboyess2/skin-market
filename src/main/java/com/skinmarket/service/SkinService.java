package com.skinmarket.service;

import com.skinmarket.dao.SkinDao;
import com.skinmarket.dao.SkinDaoImpl;
import com.skinmarket.model.Skin;

import java.util.List;

public class SkinService {
    private final SkinDao skinDao;

    public SkinService() {
        this.skinDao = new SkinDaoImpl();
    }

    public List<Skin> getAllSkins() {
        return skinDao.findAll();
    }

    public Skin getById(int id) throws Exception {
        Skin skin = skinDao.findById(id);
        if (skin == null) {
            throw new Exception("Скин не найден");
        }
        return skin;
    }

    public void addSkin(Skin skin) {
        skinDao.save(skin);
    }
}