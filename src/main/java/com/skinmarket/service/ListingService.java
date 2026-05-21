package com.skinmarket.service;

import com.skinmarket.dao.ListingDao;
import com.skinmarket.dao.ListingDaoImpl;
import com.skinmarket.dao.ListingImageDao;
import com.skinmarket.dao.ListingImageDaoImpl;
import com.skinmarket.model.Listing;
import com.skinmarket.model.ListingImage;

import java.util.List;

public class ListingService {
    private final ListingDao listingDao;
    private final ListingImageDao imageDao;

    public ListingService() {
        this.listingDao = new ListingDaoImpl();
        this.imageDao = new ListingImageDaoImpl();
    }

    // Создание объявления пользователем
    public void createUserListing(Listing listing, List<String> imagePaths) throws Exception {
        listing.setSellerType("USER");
        listing.setStatus("ACTIVE");
        listingDao.save(listing);

        if (imagePaths != null && !imagePaths.isEmpty()) {
            for (int i = 0; i < imagePaths.size(); i++) {
                ListingImage img = new ListingImage();
                img.setListingId(listing.getId());
                img.setImagePath(imagePaths.get(i));
                img.setPrimary(i == 0);
                imageDao.save(img);
            }
        }
    }

    // Создание объявления модератором от имени сайта
    public void createSiteListing(Listing listing, List<String> imagePaths, int moderatorId) throws Exception {
        listing.setSellerType("SITE");
        listing.setAddedByModeratorId(moderatorId);
        listing.setStatus("ACTIVE");
        listingDao.save(listing);

        if (imagePaths != null && !imagePaths.isEmpty()) {
            for (int i = 0; i < imagePaths.size(); i++) {
                ListingImage img = new ListingImage();
                img.setListingId(listing.getId());
                img.setImagePath(imagePaths.get(i));
                img.setPrimary(i == 0);
                imageDao.save(img);
            }
        }
    }

    // Все активные объявления
    public List<Listing> getActiveListings() {
        return listingDao.findAllActiveListing();
    }

    // Объявления конкретного пользователя
    public List<Listing> getUserListings(int userId) {
        return listingDao.findListingBySeller(userId);
    }

    // Объявления конкретного модератора
    public List<Listing> getModeratorListings(int moderatorId) {
        return listingDao.findListingByModerator(moderatorId);
    }

    // Изображения объявления
    public List<ListingImage> getImages(int listingId) {
        return imageDao.findByListingId(listingId);
    }

    // Получить объявление по id
    public Listing getListing(int id) throws Exception {
        Listing listing = listingDao.findById(id);
        if (listing == null) {
            throw new Exception("Объявление не найдено");
        }
        return listing;
    }

    // Заморозить/разморозить своё объявление
    public void freezeListing(int listingId, int userId) throws Exception {
        Listing listing = listingDao.findById(listingId);
        if (listing == null) {
            throw new Exception("Объявление не найдено");
        }
        if (!listing.getSellerType().equals("USER") || listing.getSellerId() == null || listing.getSellerId() != userId) {
            throw new Exception("Вы не можете изменить это объявление");
        }
        if (listing.getStatus().equals("ACTIVE")) {
            listingDao.updateStatus(listingId, "FROZEN");
        } else if (listing.getStatus().equals("FROZEN")) {
            listingDao.updateStatus(listingId, "ACTIVE");
        }
    }

    // Пользователь удаляет своё объявление
    public void deleteByUser(int listingId, int userId) throws Exception {
        Listing listing = listingDao.findById(listingId);
        if (listing == null) {
            throw new Exception("Объявление не найдено");
        }
        if (!listing.getSellerType().equals("USER") || listing.getSellerId() == null || listing.getSellerId() != userId) {
            throw new Exception("Вы не можете удалить это объявление");
        }
        listingDao.updateStatus(listingId, "DELETED_BY_USER");
    }

    // Модератор удаляет любое объявление
    public void deleteByModerator(int listingId) throws Exception {
        Listing listing = listingDao.findById(listingId);
        if (listing == null) {
            throw new Exception("Объявление не найдено");
        }
        listingDao.updateStatus(listingId, "DELETED_BY_MODERATOR");
    }
}