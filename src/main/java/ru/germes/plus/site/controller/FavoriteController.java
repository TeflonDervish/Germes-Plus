package ru.germes.plus.site.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.persons.IndividualPerson;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {


    @GetMapping
    public String getFavorite(
            @AuthenticationPrincipal IndividualPerson individualPerson,
            Model model) {

        return "my_favorite.html";
    }
}
