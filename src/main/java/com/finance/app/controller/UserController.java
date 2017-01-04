package com.finance.app.controller;

import com.finance.app.domain.MessageToEmail;
import com.finance.app.domain.User;
import com.finance.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class UserController extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        LOGGER.info("UserController.getRegistration()");
        model.addAttribute("registration", new User());
        LOGGER.info("UserController.getRegistration() finished");
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        LOGGER.info("UserController.saveUser()");
        userService.saveUser(user);
        if (bindingResult.hasErrors()) {
            LOGGER.info("UserController.saveUser() some error in form");
            return "redirect/:registration";
        } else {
            jmsTemplate.convertAndSend("mailbox", new MessageToEmail(user.getEmail(), "Registration is success"));
            LOGGER.info("UserController.saveUser() mail sent to user :" + user.getEmail() + " ");
            LOGGER.info("UserController.saveUser() finished");
            return "login";
        }
    }
}
