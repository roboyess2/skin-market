package com.skinmarket.dao;

import com.skinmarket.model.Article;
import com.skinmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {

    @Override
    public List<Article> findAllArticles() {
        List<Article> list = new ArrayList<>();
        String sql = "SELECT id, title, content, preview_image, author_id, status, views_count, created_at, updated_at FROM articles WHERE status='PUBLISHED' ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapArticle(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Article> findByAuthor(int authorId) {
        List<Article> list = new ArrayList<>();
        String sql = "SELECT id, title, content, preview_image, author_id, status, views_count, created_at, updated_at FROM articles WHERE author_id=? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapArticle(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Article findById(int id) {
        String sql = "SELECT id, title, content, preview_image, author_id, status, views_count, created_at, updated_at FROM articles WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapArticle(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(Article article) {
        String sql = "INSERT INTO articles (title, content, preview_image, author_id, status) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setString(3, article.getPreviewImage());
            ps.setInt(4, article.getAuthorId());
            ps.setString(5, article.getStatus());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                article.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Article article) {
        String sql = "UPDATE articles SET title=?, content=?, preview_image=?, status=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setString(3, article.getPreviewImage());
            ps.setString(4, article.getStatus());
            ps.setInt(5, article.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM articles WHERE id=?";
        try (Connection conn = DBConnection.DbConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Article mapArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setPreviewImage(rs.getString("preview_image"));
        article.setAuthorId(rs.getInt("author_id"));
        article.setStatus(rs.getString("status"));
        article.setViewsCount(rs.getInt("views_count"));
        article.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        article.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return article;
    }
}