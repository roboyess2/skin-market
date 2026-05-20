package com.skinmarket.dao;

import com.skinmarket.model.ListingImage;
import java.util.List;

public interface ListingImageDao {
    List<ListingImage> findByListingId(int listingId);
    void save(ListingImage image);
    void deleteByListingId(int listingId);
}