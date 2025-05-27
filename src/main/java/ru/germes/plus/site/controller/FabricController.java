package ru.germes.plus.site.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.service.FabricService;

@Controller
@RequestMapping("/fabric")
public class FabricController {

    private final FabricService fabricService;

    public FabricController(FabricService fabricService) {
        this.fabricService = fabricService;
    }

    @GetMapping
    public String getFabric(Model model) {

        Fabric fabric = fabricService.getFabric();

        model.addAttribute("pointOnTheMap", fabric.getPointOnTheMap());
        model.addAttribute("name", fabric.getName());
        model.addAttribute("email", fabric.getEmail());
        model.addAttribute("phone_number", fabric.getPhoneNumber());
        model.addAttribute("address", fabric.getAddress());

        return "cardForFabric";
    }
}
