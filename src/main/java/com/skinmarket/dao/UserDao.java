package com.skinmarket.dao;

import com.skinmarket.model.User;
import java.util.Optional;

public interface UserDao {
    User findByUsername(String username);
    User findById(int id);
    void save(User user);          // вставляет и устанавливает id
    void update(User user);        // обновляет данные
    void deactivate(int userId);   // блокировка
}