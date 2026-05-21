package com.skinmarket.controller;

import com.skinmarket.model.Article;
import com.skinmarket.model.User;
import com.skinmarket.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/moderator/articles")
public class ModeratorArticleServlet extends HttpServlet {
    private ArticleService articleService;

    @Override
    public void init() {
        articleService = new ArticleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Article article = articleService.getById(id);
                req.setAttribute("article", article);
                req.getRequestDispatcher("/WEB-INF/views/moderator/article-form.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                resp.sendRedirect(req.getContextPath() + "/moderator/articles");
            }
        } else if ("new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/moderator/article-form.jsp").forward(req, resp);
        } else {
            List<Article> articles = articleService.getAllPublished();
            req.setAttribute("articles", articles);
            req.getRequestDispatcher("/WEB-INF/views/moderator/articles.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        String action = req.getParameter("action");

        if ("save".equals(action)) {
            String idParam = req.getParameter("id");
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            Article article;
            if (idParam != null && !idParam.isEmpty()) {
                // Обновление существующей
                try {
                    article = articleService.getById(Integer.parseInt(idParam));
                } catch (Exception e) {
                    article = new Article();
                }
            } else {
                article = new Article();
            }

            article.setTitle(title);
            article.setContent(content);
            article.setAuthorId(user.getId());

            if (idParam != null && !idParam.isEmpty()) {
                articleService.update(article);
            } else {
                articleService.create(article);
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            articleService.delete(id);
        }

        resp.sendRedirect(req.getContextPath() + "/moderator/articles");
    }
}