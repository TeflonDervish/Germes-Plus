package ru.germes.plus.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.Korzina;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.service.KorzinaService;

@Controller
@RequestMapping("/korzina")
public class KorzinaController {

    @Autowired
    private KorzinaService korzinaService;

    @GetMapping
    public String getKorzina(Model model,
                             @AuthenticationPrincipal IndividualPerson individualPerson) {
        Korzina korzina = korzinaService.getKorzina(individualPerson);

        model.addAttribute("products", korzina.getProducts());

        return "korzina.html";
    }

    @PostMapping("{id}/add_to_korzina")
    public String addToKorzina(@AuthenticationPrincipal IndividualPerson individualPerson,
                               @PathVariable Long id) {
        korzinaService.addProduct(id, individualPerson);

        return "redirect:/korzina";
    }

}
