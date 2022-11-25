package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    List<User> listUsers();

    void saveUser(User person);

    void removeUser(Long id);

    //void updateUser(User user);
}
