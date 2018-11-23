package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.service.CategoryService;
import ru.fonzy.fnotes.service.UserService;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/register")
    public String registration(){
        return "/register";
    }

//    @PostMapping("/register")
//    public String register(User user, Model model){
//        boolean result = userService.addUser(user);
//
//        System.out.println();
//
//        if (!result) {
//            model.addAttribute("message", "Такой пользователь уже существует");
//            return "register";
//        }
//
//        return "redirect:/login";
//    }

    @PostMapping("/register")
    public String register(@RequestParam Map<String, String> form){
        System.out.println();

        return "/redirect:/login";
    }
}
