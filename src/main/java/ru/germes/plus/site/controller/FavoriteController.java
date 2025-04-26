package ru.germes.plus.site.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.repository.LikesRepository;
import ru.germes.plus.site.service.LikesService;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private LikesService likesService;

    @GetMapping
    public String getFavorite(
            @AuthenticationPrincipal IndividualPerson individualPerson,
            Model model) {

        model.addAttribute("products", likesService.getProductForIndividuals(individualPerson));
        return "my_favorite.html";
    }

    @PostMapping("/{id}/unlike")
    public String unlike(
            @AuthenticationPrincipal IndividualPerson individualPerson,
            @PathVariable Long id
    ){
        likesService.deleteLike(id, individualPerson.getId());
        return "redirect:/favorite";
    }
}
