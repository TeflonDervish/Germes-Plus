package ru.germes.plus.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Main {

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login.html";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        return "registration.html";
    }
}
