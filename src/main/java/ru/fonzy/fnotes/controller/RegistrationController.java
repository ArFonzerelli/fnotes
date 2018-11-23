package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.UserDto;
import ru.fonzy.fnotes.service.CategoryService;
import ru.fonzy.fnotes.service.UserService;

import javax.validation.Valid;
import java.util.List;
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
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();

            for (ObjectError error : errors){
                String field = ((FieldError) error).getField();
                model.addAttribute(field.concat("_failed"), error.getDefaultMessage());
            }

            return "/register";
        }

        boolean result = userService.addUser(userDto);

        if (!result) {
            model.addAttribute("user_exists", "Такой логин уже существует");
            return "/register";
        }

        return "redirect:/login";
    }
}
