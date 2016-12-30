package com.finance.app.controller;

import com.finance.app.domain.User;
import com.finance.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        LOGGER.info("UserController.getRegistration()");
        model.addAttribute("registration", new User());
        LOGGER.info("UserController.getRegistration() finished");
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute User user) {
        LOGGER.info("UserController.save()");
        userService.save(user);
        LOGGER.info("UserController.save() finished");
        return "login";
    }
}
