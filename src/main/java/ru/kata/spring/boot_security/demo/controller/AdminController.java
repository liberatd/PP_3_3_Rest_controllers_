package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceInterface;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;


@Controller
@RequestMapping("/users")
public class AdminController {

    private final UserServiceInterface userServiceInterface;
    private final RoleServiceInterface roleServiceInterface;

    @Autowired
    public AdminController(UserServiceInterface userServiceInterface, RoleServiceInterface roleServiceInterface) {
        this.userServiceInterface = userServiceInterface;
        this.roleServiceInterface = roleServiceInterface;
    }


    @GetMapping()
    public String getAllUsers (Model model) {
        model.addAttribute("user", userServiceInterface.getAllUsers());
        return "admin/index";
    }

    @GetMapping("/new")
    public String newUser (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleServiceInterface.getAllRole());
        return "admin/new";
    }

    @PostMapping()
    public String add (@ModelAttribute("user") User user) {
        userServiceInterface.add(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit (@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userServiceInterface.getById(id));
        model.addAttribute("role", roleServiceInterface.getAllRole());
        return "admin/edit";
    }

    @PostMapping("/edit")
    public String update (@ModelAttribute ("user") User user, @RequestParam(value = "id") long id) {
        userServiceInterface.update(user, id);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete (@RequestParam(value = "id") long id) {
        userServiceInterface.delete(id);
        return "redirect:/users";
    }
}