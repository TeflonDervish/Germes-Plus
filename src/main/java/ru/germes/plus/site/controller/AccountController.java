package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    private final IndividualPersonService individualPersonService;

    private final ProductForIndividualService productForIndividualService;

    private final LikesService likesService;

    public AccountController(
            IndividualPersonService individualPersonService,
            ProductForIndividualService productForIndividualService,
            LikesService likesService) {
        this.individualPersonService = individualPersonService;
        this.productForIndividualService = productForIndividualService;
        this.likesService = likesService;
    }

    @GetMapping
    public String getAccount(@AuthenticationPrincipal IndividualPerson account,
                             Model model) {

        IndividualPerson individualPerson = individualPersonService.getById(account.getId());

        model.addAttribute("name", individualPerson.getName());
        model.addAttribute("email", individualPerson.getEmail());
        model.addAttribute("surname", individualPerson.getSurname());
        model.addAttribute("phone", individualPerson.getPhone());
        model.addAttribute("city", individualPerson.getCity());

        log.info(individualPerson);

        return "myAccount";
    }

    @PostMapping("/edit")
    public String edit(@AuthenticationPrincipal IndividualPerson individualPerson,
                       @ModelAttribute IndividualPerson newData) {
        individualPersonService.save(individualPerson. getId(), newData);
        return "redirect:/account";
    }

}
