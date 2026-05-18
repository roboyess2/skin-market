package com.skinmarket.model;

import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String username;
    private String passwordHash;
    private String email;
    private String role; // USER / MODERATOR
    private LocalDateTime createdAt;
}
