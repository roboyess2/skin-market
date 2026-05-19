package com.skinmarket.model;

import java.time.LocalDateTime;

public class Skin {
    private Integer id;
    private String name;
    private String weapon;
    private String rarity;
    private String exterior;
    private String collectionName;
    private String description;
    private String imagePath;
    private boolean isStattrak;
    private boolean isSouvenir;
    private LocalDateTime createdAt;

    public Skin() {}


    public Integer getId() {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getRarity() {
        return rarity;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getExterior() {
        return exterior;
    }
    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getCollectionName() {
        return collectionName;
    }
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isStattrak() {
        return isStattrak;
    }
    public void setStattrak(boolean statTrak) {
        isStattrak = statTrak;
    }

    public boolean isSouvenir() {
        return isSouvenir;
    }
    public void setSouvenir(boolean souvenir) {
        isSouvenir = souvenir;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}