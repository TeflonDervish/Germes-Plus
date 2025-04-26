package ru.germes.plus.site.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.IndividualPersonService;
import ru.germes.plus.site.service.LikesService;
import ru.germes.plus.site.service.ProductForIndividualService;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Log log = LogFactory.getLog(AccountController.class);
    @Autowired
    private IndividualPersonService individualPersonService;

    @Autowired
    private ProductForIndividualService productForIndividualService;

    @Autowired
    private LikesService likesService;

    @GetMapping
    public String getAccount(@AuthenticationPrincipal IndividualPerson individualPerson,
                             Model model) {

        model.addAttribute("name", individualPerson.getName());
        log.info(individualPerson.getName());

        return "myAccount.html";
    }

}
