package com.skinmarket.controller;

import com.skinmarket.model.Listing;
import com.skinmarket.model.User;
import com.skinmarket.service.ListingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-listings")
public class MyListingsServlet extends HttpServlet {
    private ListingService listingService;

    @Override
    public void init() {
        listingService = new ListingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        List<Listing> listings = listingService.getUserListings(user.getId());
        req.setAttribute("listings", listings);
        req.getRequestDispatcher("/WEB-INF/views/my-listings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        String action = req.getParameter("action");
        int listingId = Integer.parseInt(req.getParameter("listingId"));

        try {
            if ("freeze".equals(action)) {
                listingService.freezeListing(listingId, user.getId());
            } else if ("delete".equals(action)) {
                listingService.deleteByUser(listingId, user.getId());
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/my-listings");
    }
}