package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(Authentication authentication, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(authentication.getName()));
        return "user";
    }

    @ModelAttribute
    public void getUserInfo( Authentication authentication, Model model) {
        model.addAttribute("usernameUser", userService.getCurrentUsername(authentication));
        model.addAttribute("rolesUser", userService.getCurrentUserRoles(authentication));
    }
}
