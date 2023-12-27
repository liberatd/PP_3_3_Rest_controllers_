package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User findByUsername(String username) {
    if (userRepository.findByUsername(username).isEmpty()) {
      throw new UsernameNotFoundException("Пользователь с таким именем не найден");
    }
    return userRepository.findByUsername(username).get();
  }

  @Override
  public User findUserById(Long id) {
    if (userRepository.findById(id).isEmpty()) {
      throw new UsernameNotFoundException("Пользователь с таким ID не найден");
    }
    return userRepository.findById(id).get();
  }

  @Transactional
  @Override
  public void update(User updatedUser) {
    userRepository.save(updatedUser);
  }

  @Transactional
  @Override
  public void add(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteById(id);
    }
  }
}