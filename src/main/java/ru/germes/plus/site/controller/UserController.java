package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.germes.plus.site.service.UserService;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService individualPersonService;

    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegistration(Model model) {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute ru.germes.plus.site.model.persons.IndividualPerson individualPerson) {
        this.individualPersonService.registerIndividualPerson(individualPerson);
        System.out.println(individualPerson);
        return "redirect:/login";
    }


}
