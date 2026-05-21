package com.skinmarket.controller;

import com.skinmarket.model.Article;
import com.skinmarket.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/articles")
public class ArticleServlet extends HttpServlet {
    private ArticleService articleService;

    @Override
    public void init() {
        articleService = new ArticleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                Article article = articleService.getById(Integer.parseInt(idParam));
                req.setAttribute("article", article);
                req.getRequestDispatcher("/WEB-INF/views/article-detail.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            }
        } else {
            List<Article> articles = articleService.getAllPublished();
            req.setAttribute("articles", articles);
            req.getRequestDispatcher("/WEB-INF/views/articles.jsp").forward(req, resp);
        }
    }
}