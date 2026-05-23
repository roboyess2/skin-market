package com.skinmarket.controller;

import com.skinmarket.model.Listing;
import com.skinmarket.model.ListingImage;
import com.skinmarket.model.User;
import com.skinmarket.service.ListingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@WebServlet({"/listings", "/listing", "/listing/create", "/listing/detail"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize = 1024 * 1024 * 10,       // 10 MB
        maxRequestSize = 1024 * 1024 * 50     // 50 MB
)
public class ListingServlet extends HttpServlet {
    private ListingService listingService;

    @Override
    public void init() {
        listingService = new ListingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();

        if ("/listings".equals(path)) {
            // Показать все активные объявления
            List<Listing> listings = listingService.getActiveListings();

            for (Listing listing : listings) {
                List<ListingImage> images = listingService.getImages(listing.getId());
                listing.setImages(images);
            }

            req.setAttribute("listings", listings);
            req.getRequestDispatcher("/WEB-INF/views/listings.jsp").forward(req, resp);

        } else if ("/listing/detail".equals(path)) {
            // Детали одного объявления
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Listing listing = listingService.getListing(id);
                List<ListingImage> images = listingService.getImages(id);
                req.setAttribute("listing", listing);
                req.setAttribute("images", images);
                req.getRequestDispatcher("/WEB-INF/views/listing-detail.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            }

        } else if ("/listing/create".equals(path)) {
            // Форма создания объявления
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            req.getRequestDispatcher("/WEB-INF/views/create-listing.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();

        if ("/listing/create".equals(path)) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }

            User user = (User) session.getAttribute("user");

            String title = req.getParameter("title");
            String description = req.getParameter("description");
            double price = Double.parseDouble(req.getParameter("price"));

            Collection<Part> fileParts = req.getParts();
            List<String> savedPaths = new ArrayList<>();

            // ПАПКА ДЛЯ СОХРАНЕНИЯ
            String uploadPath = "A:/uploads/listings/";

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            System.out.println("Upload to: " + uploadPath);

            for (Part part : fileParts) {
                if (part.getName().equals("images") && part.getSize() > 0) {
                    String fileName = UUID.randomUUID().toString() + "_" + Paths.get(part.getSubmittedFileName()).getFileName();
                    String filePath = uploadPath + fileName;
                    part.write(filePath);

                    savedPaths.add("listings/" + fileName);
                    System.out.println("Saved file: " + filePath);
                    System.out.println("Full path: " + filePath);
                }
            }

            Listing listing = new Listing();
            listing.setSellerId(user.getId());
            listing.setTitle(title);
            listing.setDescription(description);
            listing.setPrice(price);

            try {
                listingService.createUserListing(listing, savedPaths);
                resp.sendRedirect(req.getContextPath() + "/listings?success=created");
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/create-listing.jsp").forward(req, resp);
            }
        }
    }

    private String getSubmittedFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String token : contentDisp.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "unknown";
    }
}