package com.skinmarket.dao;

import com.skinmarket.model.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByUsername(String username);
    Optional<User> findById(int id);
    void save(User user);          // вставляет и устанавливает id
    void update(User user);        // обновляет данные
    void deactivate(int userId);   // блокировка
}