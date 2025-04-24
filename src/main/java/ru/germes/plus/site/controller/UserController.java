package ru.germes.plus.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login.html";
    }

    @GetMapping("/register")
    public String getRegistration(Model model) {
        return "registration.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute IndividualPerson individualPerson) {
        userService.registerIndividualPerson(individualPerson);
        System.out.println(individualPerson);
        return "redirect:/login";
    }


}
