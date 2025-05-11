package ru.germes.plus.site.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getAccount(@AuthenticationPrincipal IndividualPerson individualPerson,
                             Model model) {

        model.addAttribute("name", individualPerson.getName());
        model.addAttribute("surname", individualPerson.getSurname());
        model.addAttribute("phone", individualPerson.getPhone());
        model.addAttribute("city", individualPerson.getCity());

        log.info(individualPerson);

        return "myAccount.html";
    }

    @PostMapping("/edit")
    public String edit(@AuthenticationPrincipal IndividualPerson individualPerson,
                       @RequestParam String name,
                       @RequestParam String surname,
                       @RequestParam String phone,
                       @RequestParam String city) {
        individualPerson.setName(name);
        individualPerson.setSurname(surname);
        individualPerson.setPhone(phone);
        individualPerson.setCity(city);
        individualPersonService.save(individualPerson);
        return "redirect:/account";
    }

}
