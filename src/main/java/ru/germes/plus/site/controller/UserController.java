package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.service.UserService;

@Controller
@AllArgsConstructor
public class UserController {

    private static final Log log = LogFactory.getLog(UserController.class);
    private UserService userService;

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
    public String registerUser(
            @ModelAttribute IndividualPerson individualPerson
    ) {
        userService.registerIndividualPerson(individualPerson);
        return "redirect:/login";
    }

    @PostMapping("/register-legal")
    public String registerLegal(
            @ModelAttribute LegalPerson legalPerson
    ) {
        log.info(legalPerson.toString());
        userService.registerLegalPerson(legalPerson);
        return "redirect:/login";
    }


}
