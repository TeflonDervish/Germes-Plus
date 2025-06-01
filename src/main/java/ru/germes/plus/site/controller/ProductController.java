package ru.germes.plus.site.controller;


import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.FeedbackService;
import ru.germes.plus.site.service.KorzinaService;
import ru.germes.plus.site.service.LikesService;
import ru.germes.plus.site.service.ProductForIndividualService;

import java.util.List;

@Controller
@RequestMapping("/sofa")
@AllArgsConstructor
public class ProductController {

    private static final Log log = LogFactory.getLog(ProductController.class);
    private ProductForIndividualService productForIndividualService;

    private LikesService likesService;

    private FeedbackService feedbackService;

    private KorzinaService korzinaService;


    @GetMapping("/{id}")
    public String getSofa(
            @AuthenticationPrincipal IndividualPerson individualPerson,
            @PathVariable Long id,
            Model model) {
        List<FeedbackOnProductForIndividual> feedbacks = feedbackService.getByProductForIndividual(id);
        ProductForIndividual productForIndividual = productForIndividualService.getById(id);

        boolean isLiked = likesService.getLike(id, individualPerson.getId()).isPresent();

        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("is_liked", isLiked);

        model.addAttribute("is_in_korzina", korzinaService.isInKorzina(id, individualPerson));

        model.addAttribute("product", productForIndividual);

        return "cardForSofa";
    }

    @PostMapping("/{id}/like")
    public String like(@AuthenticationPrincipal IndividualPerson individualPerson,
                       @PathVariable Long id) {
        likesService.addLike(id, individualPerson);

        return "redirect:/sofa/" + id;
    }

    @PostMapping("/{id}/unlike")
    public String unlike(@AuthenticationPrincipal IndividualPerson individualPerson,
                         @PathVariable Long id) {
        likesService.deleteLike(id, individualPerson.getId());

        return "redirect:/sofa/" + id;
    }

    @PostMapping("{id}/feedback")
    public String feedBack(@RequestParam("text") String text,
                           @AuthenticationPrincipal IndividualPerson individualPerson,
                           @PathVariable Long id) {
        feedbackService.sendFeedback(id, individualPerson, text);
        return "redirect:/sofa/" + id;
    }

}
