package ru.fonzy.fnotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String baseRedirect(){
        return "redirect:/notes/all";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/login_failed")
    public String loginFailed(Model model){
        model.addAttribute("message", "Вы ввели неверное имя пользователя или пароль.");
        return "login";
    }

}
