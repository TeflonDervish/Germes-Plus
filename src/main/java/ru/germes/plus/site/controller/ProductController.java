package ru.germes.plus.site.controller;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.LikesService;
import ru.germes.plus.site.service.ProductForIndividualService;
import ru.germes.plus.site.utils.Util;

@Controller
@RequestMapping("/sofa")
public class ProductController {

    private static final Log log = LogFactory.getLog(ProductController.class);
    @Autowired
    private ProductForIndividualService productForIndividualService;

    @Autowired
    private LikesService likesService;


    @GetMapping("/{id}")
    public String getSofa(
            @AuthenticationPrincipal IndividualPerson individualPerson,
            @PathVariable Long id,
                          Model model) {
        ProductForIndividual productForIndividual = productForIndividualService.getById(id);
        boolean isLiked = likesService.getLike(id, individualPerson.getId()).isPresent();
        log.info(isLiked);
        model.addAttribute("is_liked", isLiked);

        model.addAttribute("product_id", productForIndividual.getId());
        model.addAttribute("urls", productForIndividual.getUrls());
        model.addAttribute("name", productForIndividual.getName());
        model.addAttribute("price", productForIndividual.getPrice());

        model.addAttribute("basis", productForIndividual.getBasis());
        model.addAttribute("filling", productForIndividual.getFilling());
        model.addAttribute("box", productForIndividual.getBox());
        model.addAttribute("mechanism", productForIndividual.getMechanism());
        model.addAttribute("armrests", productForIndividual.getArmrests());
        model.addAttribute("seatDepth", productForIndividual.getDepth());
        model.addAttribute("planting", productForIndividual.getPlanting());
        model.addAttribute("overallDimensions", productForIndividual.getOverallDimensions());
        model.addAttribute("sleepingSpace", productForIndividual.getSleepingSpace());
        model.addAttribute("depth", productForIndividual.getSeatDepth());
        model.addAttribute("configuration", productForIndividual.getConfiguration());

        model.addAttribute("article", productForIndividual.getArticle());
        model.addAttribute("description", productForIndividual.getDescription());
        return "cardForSofa.html";
    }

    @PostMapping("/{id}/like")
    public String like(@AuthenticationPrincipal IndividualPerson individualPerson,
                       @PathVariable Long id) {
        likesService.addLike(id, individualPerson.getId());

        return "redirect:/sofa/" + id;
    }

    @PostMapping("/{id}/unlike")
    public String unlike(@AuthenticationPrincipal IndividualPerson individualPerson,
                       @PathVariable Long id) {
        likesService.deleteLike(id, individualPerson.getId());

        return "redirect:/sofa/" + id;
    }
}
