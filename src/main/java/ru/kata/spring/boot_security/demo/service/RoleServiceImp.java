package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositories;
import java.util.List;

@Service
public class RoleServiceImp implements RoleServiceInterface {

    private final RoleRepositories roleRepositories;

    @Autowired
    public RoleServiceImp(RoleRepositories roleRepositories) {
        this.roleRepositories = roleRepositories;
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepositories.findAll();
    }
}
