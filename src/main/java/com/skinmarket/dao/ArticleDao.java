package com.skinmarket.dao;

import com.skinmarket.model.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleDao {
    List<Article> findAllArticles();
    List<Article> findByAuthor(int authorId);
    Article findById(int id);
    void save(Article article);
    void update(Article article);
    void delete(int id);
}