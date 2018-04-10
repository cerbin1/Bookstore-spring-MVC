package com.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {
    @RequestMapping
    public String welcome(Model model) {
        model.addAttribute("greeting", "Welcome!");
        model.addAttribute("shop", "Store");
        return "welcome";
    }
}