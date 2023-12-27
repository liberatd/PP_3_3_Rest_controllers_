package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  User findByUsername(String username);

  User findUserById(Long id);

  void update(User user);

  void add(User user);

  void delete(Long id);

}