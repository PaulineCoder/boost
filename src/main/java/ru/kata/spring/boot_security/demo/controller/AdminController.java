package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }

    @PostMapping("/addNew")
    public String addNew(User user) {
        userService.add(user);
        return "redirect:/admin/getAll";
    }

    @PutMapping(value = "/update")
    public String update(User user) {
        userService.update(user);
        return "redirect:/admin/getAll";
    }

    @DeleteMapping(value = "/delete")
    public String delete(long id) {
        userService.delete(id);
        return "redirect:/admin/getAll";
    }

    @ModelAttribute
    public void getUsername( Authentication authentication, Model model) {
        model.addAttribute("usernameUser", userService.getCurrentUsername(authentication));
        model.addAttribute("rolesUser", userService.getCurrentUserRoles(authentication));
    }
}
