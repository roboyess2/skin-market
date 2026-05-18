package com.skinmarket.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseMigrator {

    public static void migrate() {

        String createUsers = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    username VARCHAR(50) UNIQUE NOT NULL,
                    password_hash VARCHAR(255) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    role VARCHAR(20) NOT NULL DEFAULT 'USER' CHECK (role IN ('USER', 'MODERATOR')),
                    avatar_path VARCHAR(255),
                    steam_id VARCHAR(50),
                    trade_link VARCHAR(500),
                    is_active BOOLEAN NOT NULL DEFAULT TRUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        String createArticles = """
                CREATE TABLE IF NOT EXISTS articles (
                    id SERIAL PRIMARY KEY,
                    title VARCHAR(200) NOT NULL,
                    content TEXT NOT NULL,
                    preview_image VARCHAR(255),
                    author_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED' CHECK (status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED')),
                    views_count INTEGER NOT NULL DEFAULT 0,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        String createSkins = """
                CREATE TABLE IF NOT EXISTS skins (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(100) UNIQUE NOT NULL,
                    weapon VARCHAR(50) NOT NULL,
                    rarity VARCHAR(30) NOT NULL CHECK (rarity IN (
                        'Consumer Grade', 'Industrial Grade', 'Mil-Spec', 
                        'Restricted', 'Classified', 'Covert', 'Rare Special'
                    )),
                    exterior VARCHAR(30) CHECK (exterior IN (
                        'Factory New', 'Minimal Wear', 'Field-Tested', 
                        'Well-Worn', 'Battle-Scarred', NULL
                    )),
                    collection_name VARCHAR(100),
                    description TEXT,
                    image_path VARCHAR(255),
                    is_stattrak BOOLEAN NOT NULL DEFAULT FALSE,
                    is_souvenir BOOLEAN NOT NULL DEFAULT FALSE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        String createListings = """
                CREATE TABLE IF NOT EXISTS listings (
                    id SERIAL PRIMARY KEY,
                    title VARCHAR(200) NOT NULL,
                    description TEXT,
                    price NUMERIC(10,2) NOT NULL CHECK (price > 0),
                    seller_type VARCHAR(10) NOT NULL CHECK (seller_type IN ('USER', 'SITE')),
                    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' 
                        CHECK (status IN ('ACTIVE', 'FROZEN', 'SOLD', 'DELETED_BY_USER', 'DELETED_BY_MODERATOR')),
                    skin_id INTEGER REFERENCES skins(id),
                    seller_id INTEGER REFERENCES users(id),
                    added_by_moderator_id INTEGER REFERENCES users(id),
                    float_value NUMERIC(6,4),
                    pattern_index INTEGER,
                    sticker_info TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    CONSTRAINT chk_seller CHECK (
                        (seller_type = 'USER' AND seller_id IS NOT NULL AND added_by_moderator_id IS NULL) OR
                        (seller_type = 'SITE' AND added_by_moderator_id IS NOT NULL AND seller_id IS NULL)
                    )
                )
                """;

        String createListingImages = """
                CREATE TABLE IF NOT EXISTS listing_images (
                    id SERIAL PRIMARY KEY,
                    listing_id INTEGER NOT NULL REFERENCES listings(id) ON DELETE CASCADE,
                    image_path VARCHAR(255) NOT NULL,
                    is_primary BOOLEAN NOT NULL DEFAULT FALSE,
                    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        String createUserBalances = """
                CREATE TABLE IF NOT EXISTS user_balances (
                    id SERIAL PRIMARY KEY,
                    user_id INTEGER NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
                    balance NUMERIC(10,2) NOT NULL DEFAULT 0.00 CHECK (balance >= 0),
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        String createTransactions = """
                CREATE TABLE IF NOT EXISTS transactions (
                    id SERIAL PRIMARY KEY,
                    listing_id INTEGER NOT NULL REFERENCES listings(id),
                    buyer_id INTEGER NOT NULL REFERENCES users(id),
                    seller_id INTEGER REFERENCES users(id),
                    amount NUMERIC(10,2) NOT NULL,
                    transaction_type VARCHAR(20) NOT NULL CHECK (transaction_type IN ('PURCHASE', 'REFUND', 'PAYOUT')),
                    status VARCHAR(20) NOT NULL DEFAULT 'COMPLETED' CHECK (status IN ('PENDING', 'COMPLETED', 'CANCELLED')),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        String createModerationLogs = """
                CREATE TABLE IF NOT EXISTS moderation_logs (
                    id SERIAL PRIMARY KEY,
                    moderator_id INTEGER NOT NULL REFERENCES users(id),
                    listing_id INTEGER REFERENCES listings(id),
                    article_id INTEGER REFERENCES articles(id),
                    action VARCHAR(50) NOT NULL CHECK (action IN (
                        'DELETE_LISTING', 'RESTORE_LISTING', 'FREEZE_LISTING', 
                        'CREATE_ARTICLE', 'EDIT_ARTICLE', 'DELETE_ARTICLE', 
                        'BAN_USER', 'UNBAN_USER'
                    )),
                    reason TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        try (Connection connection = DBConnection.DbConnect();
             Statement sqlQuery = connection.createStatement()) {

            System.out.println("Starting database migration...");

            sqlQuery.execute(createUsers);
            System.out.println("Table users created");

            sqlQuery.execute(createArticles);
            System.out.println("Table articles created");

            sqlQuery.execute(createSkins);
            System.out.println("Table skins created");

            sqlQuery.execute(createListings);
            System.out.println("Table listings created");

            sqlQuery.execute(createListingImages);
            System.out.println("Table listing_images created");

            sqlQuery.execute(createUserBalances);
            System.out.println("✓ Table 'user_balances' created");

            sqlQuery.execute(createTransactions);
            System.out.println("Table transactions created");

            sqlQuery.execute(createModerationLogs);
            System.out.println("Table moderation_logs created");

            System.out.println("Database migration completed successfully!");

        } catch (SQLException e) {
            System.err.println("Migration failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database migration failed", e);
        }
    }
}