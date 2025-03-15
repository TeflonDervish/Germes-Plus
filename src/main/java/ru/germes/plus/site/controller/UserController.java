package ru.germes.plus.site.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.service.UserService;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register-individual")
    public String registerUser(@ModelAttribute IndividualPerson individualPerson) {
        userService.registerIndividualPerson(individualPerson);
        System.out.println(individualPerson);
        log.info(individualPerson.toString());
        return "redirect:/login";
    }


}
