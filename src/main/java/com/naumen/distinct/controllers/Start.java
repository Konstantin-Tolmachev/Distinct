package com.naumen.distinct.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Start {

    @GetMapping("/")
    public String start( Model model) {
        model.addAttribute("title", "Старт");
        return "startHTML/start";
    }
}
