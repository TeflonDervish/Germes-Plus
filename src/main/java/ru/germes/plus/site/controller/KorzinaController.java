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
import ru.germes.plus.site.model.products.ProductForIndividual;
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

        double totalPrice = 0;
        for (ProductForIndividual product : korzina.getProducts())
            totalPrice += product.getPrice();

        model.addAttribute("products", korzina.getProducts());

        model.addAttribute("total_price", totalPrice);

        model.addAttribute("product_count", korzina.getProducts().size());


        return "korzina.html";
    }

    @PostMapping("{id}/add_to_korzina")
    public String addToKorzina(@AuthenticationPrincipal IndividualPerson individualPerson,
                               @PathVariable Long id) {
        korzinaService.addProduct(id, individualPerson);

        return "redirect:/korzina";
    }

    @PostMapping("{id}/delete_from_korzina")
    public String removeFromKorzina(@AuthenticationPrincipal IndividualPerson individualPerson,
                                    @PathVariable Long id) {
        korzinaService.deleteProduct(id, individualPerson);
        return "redirect:/korzina";
    }

}
