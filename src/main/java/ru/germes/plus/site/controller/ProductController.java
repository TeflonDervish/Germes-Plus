package ru.germes.plus.site.controller;


import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.service.*;

import java.util.List;

@Controller
@RequestMapping("/sofa")
@AllArgsConstructor
public class ProductController {

    private static final Log log = LogFactory.getLog(ProductController.class);
    private final ProductForLegalService productForLegalService;
    private ProductForIndividualService productForIndividualService;

    private LikesForIndividualService likesForIndividualService;
    private LikesForLegalService likesForLegalService;

    private FeedbackForIndividualService feedbackForIndividualService;
    private FeedbackForLegalService feedbackForLegalService;

    private KorzinaForIndividualService korzinaForIndividualService;
    private KorzinaForLegalService korzinaForLegalService;


    @GetMapping("/{id}")
    public String getSofa(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable Long id,
            Model model) {

        if (user instanceof IndividualPerson individualPerson) {
            List<FeedbackOnProductForIndividual> feedbacks = feedbackForIndividualService.getByProductForIndividual(id);
            ProductForIndividual productForIndividual = productForIndividualService.getById(id);

            boolean isLiked = likesForIndividualService.getLike(id, individualPerson.getId()).isPresent();

            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("is_liked", isLiked);

            model.addAttribute("is_in_korzina", korzinaForIndividualService.isInKorzina(id, individualPerson));

            model.addAttribute("product", productForIndividual);

            return "cardForSofa";
        } else if (user instanceof LegalPerson legalPerson) {
            List<FeedbackOnProductForLegal> feedbacks = feedbackForLegalService.getByProductForLegalId(id);
            ProductForLegal product = productForLegalService.getById(id);

            boolean isLiked = likesForLegalService.getLike(id, legalPerson.getId()).isPresent();

            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("is_liked", isLiked);

            model.addAttribute("is_in_korzina", korzinaForLegalService.isInKorzina(id, legalPerson));

            model.addAttribute("product", product);

            return "forLegalPerson/card";
        }
        return "redirect:/login";
    }

    @PostMapping("/{id}/like")
    public String like(@AuthenticationPrincipal UserDetails user,
                       @PathVariable Long id) {
        if (user instanceof IndividualPerson individualPerson) {
            likesForIndividualService.addLike(id, individualPerson);
        } else if (user instanceof LegalPerson legalPerson) {
            likesForLegalService.addLike(id, legalPerson);
        }

        return "redirect:/sofa/" + id;
    }

    @PostMapping("/{id}/unlike")
    public String unlike(@AuthenticationPrincipal UserDetails user,
                         @PathVariable Long id) {

        if (user instanceof IndividualPerson individualPerson) {
            likesForIndividualService.deleteLike(id, individualPerson.getId());
        } else if (user instanceof LegalPerson legalPerson) {
            likesForLegalService.deleteLike(id, legalPerson.getId());
        }

        return "redirect:/sofa/" + id;
    }

    @PostMapping("{id}/feedback")
    public String feedBack(@RequestParam("text") String text,
                           @AuthenticationPrincipal IndividualPerson individualPerson,
                           @PathVariable Long id) {
        feedbackForIndividualService.sendFeedback(id, individualPerson, text);
        return "redirect:/sofa/" + id;
    }

    @PostMapping("{id}/feedback-for-legal")
    public String feedbackForLegal(
            @RequestParam String text,
            @AuthenticationPrincipal LegalPerson legalPerson,
            @PathVariable Long id
    ) {
        feedbackForLegalService.sendFeedback(id, legalPerson, text);
        return "redirect:/sofa/" + id;
    }

}
