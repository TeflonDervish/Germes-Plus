package ru.germes.plus.site.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.service.LikesForIndividualService;
import ru.germes.plus.site.service.LikesForLegalService;

@Controller
@RequestMapping("/favorite")
@AllArgsConstructor
public class FavoriteController {

    private LikesForIndividualService likesForIndividualService;
    private LikesForLegalService likesForLegalService;

    @GetMapping
    public String getFavorite(
            @AuthenticationPrincipal UserDetails user,
            Model model) {

        if (user instanceof IndividualPerson individualPerson) {
            model.addAttribute("products", likesForIndividualService.getProductForIndividuals(individualPerson));
            return "my_favorite";
        } else if (user instanceof LegalPerson legalPerson) {
            model.addAttribute("products", likesForLegalService.getProductForIndividuals(legalPerson));
            return "forLegalPerson/my_favorite";
        }
        return "redirect:/login";
    }

    @PostMapping("/{id}/unlike")
    public String unlike(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable Long id
    ) {

        if (user instanceof IndividualPerson individualPerson) {
            likesForIndividualService.deleteLike(id, individualPerson.getId());
        } else if (user instanceof LegalPerson legalPerson) {
            likesForLegalService.deleteLike(id, legalPerson.getId());
        }
        return "redirect:/favorite";
    }
}
