package ru.germes.plus.site.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.service.ProductForIndividualService;

@Controller
@RequestMapping("/account")
public class AccountController {

    private ProductForIndividualService productForIndividualService;

    public AccountController(
            ProductForIndividualService productForIndividualService) {
        this.productForIndividualService = productForIndividualService;
    }

    @GetMapping
    public String getAccount(@AuthenticationPrincipal IndividualPerson individualPerson,
                             Model model) {



        return "myAccount.html";
    }

}
