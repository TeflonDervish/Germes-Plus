package ru.germes.plus.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Main {

    @GetMapping
    public String hello() {
        return "index";
    }
}
