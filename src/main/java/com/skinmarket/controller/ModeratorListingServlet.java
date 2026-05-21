package com.skinmarket.controller;

import com.skinmarket.model.Listing;
import com.skinmarket.service.ListingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/moderator/listings")
public class ModeratorListingServlet extends HttpServlet {
    private ListingService listingService;

    @Override
    public void init() {
        listingService = new ListingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Listing> listings = listingService.getActiveListings();
        req.setAttribute("listings", listings);
        req.getRequestDispatcher("/WEB-INF/views/moderator/listings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        int listingId = Integer.parseInt(req.getParameter("listingId"));

        try {
            if ("delete".equals(action)) {
                listingService.deleteByModerator(listingId);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/moderator/listings");
    }
}