package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    void delete(Long id);
    User findUserByEmail(String email);
    Optional<User> findUserById(Long id);
    List<User> findAll();
}
