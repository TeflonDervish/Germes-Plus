package ru.germes.plus.site.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.ProductForIndividualService;

@Controller
public class ProductController {

    @Autowired
    private ProductForIndividualService productForIndividualService;


    @GetMapping("/sofa/{id}")
    public String getSofa(@PathVariable Long id,
                          Model model) {
        ProductForIndividual productForIndividual = productForIndividualService.getById(id);
        model.addAttribute("url", productForIndividual.getUrl());
        model.addAttribute("name", productForIndividual.getName());
        model.addAttribute("price", productForIndividual.getPrice());

        return "cardForSofa.html";
    }
}
