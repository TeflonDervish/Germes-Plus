package ru.germes.plus.site.controller;


import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.germes.plus.site.dto.FilterProductForIndividual;
import ru.germes.plus.site.dto.FilterProductForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.service.ProductForIndividualService;
import ru.germes.plus.site.service.ProductForLegalService;

import java.util.List;

@Controller
@RequestMapping("/catalog")
@AllArgsConstructor
public class CatalogController {

    private static final Log log = LogFactory.getLog(CatalogController.class);
    private final ProductForIndividualService productForIndividualService;
    private final ProductForLegalService productForLegalService;


    @GetMapping
    public String getCatalog(
            Model model,
            @AuthenticationPrincipal UserDetails user
    ) {
        log.info("Выдана страница каталога");

        model.addAttribute("products", productForIndividualService.getAll());
        if (user instanceof IndividualPerson) {
            model.addAttribute("filter", new FilterProductForIndividual());
            return "catalog";
        } else if (user instanceof LegalPerson) {
            model.addAttribute("products", productForLegalService.getAll());
            return "forLegalPerson/catalog";
        }
        return "catalog";
    }

    @PostMapping("/search")
    public String getCatalogByText(Model model,
                                   @RequestParam("search") String search) {
        model.addAttribute("products", productForIndividualService.getBySearch(search));
        return "catalog";
    }

    @PostMapping("/filter")
    public String filter(
            @ModelAttribute FilterProductForIndividual filter,
            Model model
    ) {
        List<ProductForIndividual> productForIndividuals = productForIndividualService.getFilteredProducts(filter);
        model.addAttribute("products", productForIndividuals);
        return "catalog";
    }

    @PostMapping("/sort")
    public String sort(
            @RequestParam String sort,
            Model model,
            @AuthenticationPrincipal UserDetails user
    ) {
        if (user instanceof IndividualPerson) {
            List<ProductForIndividual> productForIndividuals = productForIndividualService.getBySort(sort);
            model.addAttribute("products", productForIndividuals);
            return "catalog";
        } else if (user instanceof LegalPerson) {
            List<ProductForLegal> products = productForLegalService.getBySort(sort);
            model.addAttribute("products", products);
            return "forLegalPerson/catalog";
        }
        return "catalog";
    }

    @PostMapping("/legal/filter")
    public String filterLegal(
            @ModelAttribute FilterProductForLegal filter,
            Model model
    ) {
        log.info(filter);
        List<ProductForLegal> productForLegals = productForLegalService.getFilteredProducts(filter);
        model.addAttribute("products", productForLegals);
        return "forLegalPerson/catalog";
    }
}
