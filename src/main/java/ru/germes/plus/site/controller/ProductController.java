package ru.germes.plus.site.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.ProductForIndividualService;
import ru.germes.plus.site.utils.Util;

@Controller
public class ProductController {

    @Autowired
    private ProductForIndividualService productForIndividualService;


    @GetMapping("/sofa/{id}")
    public String getSofa(@PathVariable Long id,
                          Model model) {
        ProductForIndividual productForIndividual = productForIndividualService.getById(id);
        model.addAttribute("urls", productForIndividual.getUrls());
        model.addAttribute("name", productForIndividual.getName());
        model.addAttribute("price", productForIndividual.getPrice());

        model.addAttribute("map", ProductForIndividualService.getCharacteristicFromJson(productForIndividual.getCharacteristics()));
        model.addAttribute("article", productForIndividual.getArticle());
        model.addAttribute("description", productForIndividual.getDescription());
        return "cardForSofa.html";
    }
}
