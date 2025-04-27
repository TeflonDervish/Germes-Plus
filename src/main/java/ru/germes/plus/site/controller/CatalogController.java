package ru.germes.plus.site.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.germes.plus.site.service.ProductForIndividualService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private static final Log log = LogFactory.getLog(CatalogController.class);
    @Autowired
    private ProductForIndividualService productForIndividualService;


    @GetMapping
    public String getCatalog(Model model) {
        log.info("Выдана страница каталога");
        model.addAttribute("products", productForIndividualService.getAll());
        return "catalog.html";
    }

    @PostMapping("/search")
    public String getCatalogByText(Model model,
                                   @RequestParam("search") String search) {
        model.addAttribute("products", productForIndividualService.getBySearch(search));
        return "catalog.html";
    }
}
