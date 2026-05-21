package com.skinmarket.service;

import com.skinmarket.dao.UserDao;
import com.skinmarket.dao.UserDaoImpl;
import com.skinmarket.dao.UserBalanceDao;
import com.skinmarket.dao.UserBalanceDaoImpl;
import com.skinmarket.model.User;
import com.skinmarket.model.UserBalance;
import com.skinmarket.util.PasswordUtil;

public class UserService {
    private final UserDao userDao;
    private final UserBalanceDao balanceDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
        this.balanceDao = new UserBalanceDaoImpl();
    }

    public void register(String username, String plainPassword, String email) throws Exception {
        if (userDao.findByUsername(username) != null) {
            throw new Exception("Пользователь с таким именем уже существует");
        }
        User user = new User(username, PasswordUtil.hash(plainPassword), email);
        user.setRole("USER");
        user.setActive(true);
        userDao.save(user);

        UserBalance balance = new UserBalance();
        balance.setUserId(user.getId());
        balance.setBalance(0.0);
        balanceDao.save(balance);
    }

    public User login(String username, String plainPassword) throws Exception {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new Exception("Неверное имя пользователя или пароль");
        }
        if (!PasswordUtil.check(plainPassword, user.getPasswordHash())) {
            throw new Exception("Неверное имя пользователя или пароль");
        }
        return user;
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public void updateProfile(User user) {
        userDao.update(user);
    }
}