package com.skinmarket.service;

import com.skinmarket.dao.ArticleDao;
import com.skinmarket.dao.ArticleDaoImpl;
import com.skinmarket.model.Article;

import java.util.List;

public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService() {
        this.articleDao = new ArticleDaoImpl();
    }

    public List<Article> getAllPublished() {
        return articleDao.findAllArticles();
    }

    public Article getById(int id) throws Exception {
        Article article = articleDao.findById(id);
        if (article == null) {
            throw new Exception("Статья не найдена");
        }
        return article;
    }

    public void create(Article article) {
        article.setStatus("PUBLISHED");
        articleDao.save(article);
    }

    public void update(Article article) {
        articleDao.update(article);
    }

    public void delete(int id) {
        articleDao.delete(id);
    }

    public List<Article> getByAuthor(int authorId) {
        return articleDao.findByAuthor(authorId);
    }
}