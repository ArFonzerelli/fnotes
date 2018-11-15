package ru.fonzy.fnotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String baseRedirect(){
        return "redirect:/notes";
    }

    @GetMapping
    public String login(Model model){
        return "login";
    }
}
