package ru.germes.plus.site.controller;


import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.germes.plus.site.dto.FilterProductForIndividual;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.ProductForIndividualService;

import java.util.List;

@Controller
@RequestMapping("/catalog")
@AllArgsConstructor
public class CatalogController {

    private static final Log log = LogFactory.getLog(CatalogController.class);
    private final ProductForIndividualService productForIndividualService;


    @GetMapping
    public String getCatalog(Model model) {
        log.info("Выдана страница каталога");
        model.addAttribute("products", productForIndividualService.getAll());
        model.addAttribute("filter", new FilterProductForIndividual());
        return "catalog";
    }

    @PostMapping("/search")
    public String getCatalogByText(Model model,
                                   @RequestParam("search") String search) {
        model.addAttribute("products", productForIndividualService.getBySearch(search));
        return "catalog";
    }

    @PostMapping("/filter")
    public String filter(@ModelAttribute FilterProductForIndividual filter, Model model) {
        List<ProductForIndividual> productForIndividuals = productForIndividualService.getFilteredProducts(filter);
        model.addAttribute("products", productForIndividuals);
        return "catalog";
    }

    @PostMapping("/sort")
    public String sort(@RequestParam String sort, Model model) {
        List<ProductForIndividual> productForIndividuals = productForIndividualService.getBySort(sort);
        model.addAttribute("products", productForIndividuals);
        return "catalog";
    }
}
