package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.service.PointOfSaleService;

import java.util.List;

@Controller
@RequestMapping("/magazine")
@AllArgsConstructor
public class MagazineController {

    private PointOfSaleService pointOfSaleService;

    @GetMapping
    public String getMagazines(Model model) {

        List<PointOfSale> pointOfSales = pointOfSaleService.getAll();

        model.addAttribute("magazines", pointOfSales);

        return "magazines";
    }

    @GetMapping("/{id}")
    public String getMagazine(@PathVariable Long id, Model model) {

        PointOfSale pointOfSale = pointOfSaleService.getById(id);

        model.addAttribute("name", pointOfSale.getName());
        model.addAttribute("address", pointOfSale.getAddress());
        model.addAttribute("point_on_the_map", pointOfSale.getPointOnTheMap());
        model.addAttribute("phone_number", pointOfSale.getPhoneNumber());
        model.addAttribute("email", pointOfSale.getEmail());

        return "cardForMagazine";
    }
}
