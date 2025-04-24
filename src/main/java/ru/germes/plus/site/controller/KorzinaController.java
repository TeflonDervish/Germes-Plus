package ru.germes.plus.site.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/korzina")
public class KorzinaController {

    @GetMapping
    public String getKorzina(Model model) {
        return "korzina.html";
    }
}
