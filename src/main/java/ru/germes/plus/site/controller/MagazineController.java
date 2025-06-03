package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.feedbacks.FeedbackOnPoint;
import ru.germes.plus.site.service.FeedbackOnPointService;
import ru.germes.plus.site.service.PointOfSaleService;

import java.util.List;

@Controller
@RequestMapping("/magazine")
@AllArgsConstructor
public class MagazineController {

    private PointOfSaleService pointOfSaleService;
    private final FeedbackOnPointService feedbackOnPointService;

    @GetMapping
    public String getMagazines(Model model) {

        List<PointOfSale> pointOfSales = pointOfSaleService.getAll();

        model.addAttribute("magazines", pointOfSales);

        return "magazines";
    }

    @GetMapping("/{id}")
    public String getMagazine(@PathVariable Long id, Model model) {

        PointOfSale pointOfSale = pointOfSaleService.getById(id);
        List<FeedbackOnPoint> feedbacks = feedbackOnPointService.getByPointOfSale(id);

        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("point", pointOfSale);

        return "cardForMagazine";
    }

    @PostMapping("/{id}/feedback")
    public String feedback(
            @RequestParam String text,
            @AuthenticationPrincipal UserDetails user,
            @PathVariable Long id,
            Model model
    ) {
        feedbackOnPointService.sendFeedback(id, user, text);
        return "redirect:/magazine/" + id;
    }
}
