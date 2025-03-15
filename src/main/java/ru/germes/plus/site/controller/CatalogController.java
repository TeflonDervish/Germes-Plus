package ru.germes.plus.site.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.ProductForIndividualService;

@Controller
public class CatalogController {

    @Autowired
    private ProductForIndividualService productForIndividualService;


    @GetMapping("/catalog")
    public String getCatalog(Model model) {
        model.addAttribute("products", productForIndividualService.getAll());
        return "catalog.html";
    }
}
