package com.skinmarket.dao;

import com.skinmarket.model.Listing;
import java.util.List;

public interface ListingDao {
    List<Listing> findAllActiveListing();
    List<Listing> findListingBySeller(int sellerId);
    List<Listing> findListingByModerator(int moderatorId);
    Listing findById(int id);
    void save(Listing listing);
    void updateStatus(int listingId, String status);
    void update(Listing listing);
    void delete(int listingId);
}