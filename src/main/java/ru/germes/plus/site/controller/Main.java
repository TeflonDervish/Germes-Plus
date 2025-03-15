package ru.germes.plus.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping
    public String main() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login.html";
    }

    @GetMapping("/register")
    public String getRegistration(Model model) {
        return "registration.html";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model) {
        return "catalog.html";
    }

    @GetMapping("/sofa")
    public String getSofa(Model model) {
        return "cardForSofa.html";
    }
}
