package com.github.novikovmn.spring1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class testController {

    @GetMapping("/test")
    public String index(Model model) {
        model.addAttribute("message", "Сейчас " + new Date());
        return "test";
    }
}
