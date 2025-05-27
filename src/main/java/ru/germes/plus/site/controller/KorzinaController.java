package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;
import ru.germes.plus.site.model.Korzina;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.KorzinaService;

@Controller
@AllArgsConstructor
public class KorzinaController {

    private KorzinaService korzinaService;

    @GetMapping("/korzina")
    public String getKorzina(Model model,
                             @AuthenticationPrincipal IndividualPerson individualPerson) {

        Korzina korzina = korzinaService.getKorzina(individualPerson);

        double totalPrice = 0;
        for (ProductForIndividual product : korzina.getProducts())
            totalPrice += product.getPrice();

        model.addAttribute("products", korzina.getProducts());

        model.addAttribute("total_price", totalPrice);

        model.addAttribute("product_count", korzina.getProducts().size());

        model.addAttribute("phone_number", individualPerson.getPhone());
        model.addAttribute("surname_name", individualPerson.getSurname() + " " + individualPerson.getName());


        return "korzina";
    }

    @PostMapping("/korzina/{id}/add_to_korzina")
    public String addToKorzina(@AuthenticationPrincipal IndividualPerson individualPerson,
                               @PathVariable Long id) {
        korzinaService.addProduct(id, individualPerson);

        return "redirect:/korzina";
    }

    @PostMapping("/korzina/{id}/delete_from_korzina")
    public String removeFromKorzina(@AuthenticationPrincipal IndividualPerson individualPerson,
                                    @PathVariable Long id) {
        korzinaService.deleteProduct(id, individualPerson);
        return "redirect:/korzina";
    }

    @PostMapping("/korzina/make-order")
    public String makeOrder(@AuthenticationPrincipal IndividualPerson individualPerson) {

        return "redirect:/account";
    }

}
