package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepositories;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserServiceInterface{

    private final UserRepositories userRepositories;

    @Autowired
    public UserServiceImp(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Transactional
    @Override
    public void add(User user) {
        userRepositories.save(user);
    }

    @Transactional
    @Override
    public void update(User user, long id) {
        user.setId(id);
        userRepositories.save(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepositories.deleteById(id);
    }

    @Override
    public User getById(long id) {
         Optional<User> foundUser = userRepositories.findById(id);
        return foundUser.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }

}