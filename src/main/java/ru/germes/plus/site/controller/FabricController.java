package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.feedbacks.FeedbackOnFabric;
import ru.germes.plus.site.service.FabricService;
import ru.germes.plus.site.service.FeedbackForFabricService;

import java.util.List;

@Controller
@RequestMapping("/fabric")
@AllArgsConstructor
public class FabricController {

    private final FabricService fabricService;
    private final FeedbackForFabricService feedbackForFabricService;

    @GetMapping
    public String getFabric(Model model) {

        Fabric fabric = fabricService.getFabric();
        List<FeedbackOnFabric> feedbacks = feedbackForFabricService.getByFabricId(fabric.getId());

        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("fabric", fabric);

        return "cardForFabric";
    }

    @PostMapping("/{id}/feedback")
    public String feedback(
            @RequestParam String text,
            @AuthenticationPrincipal UserDetails user,
            @PathVariable Long id,
            Model model
    ) {
        feedbackForFabricService.sendFeedback(id, user, text);
        return "redirect:/fabric";
    }
}
