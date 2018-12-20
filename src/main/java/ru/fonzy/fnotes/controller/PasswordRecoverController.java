package ru.fonzy.fnotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fonzy.fnotes.dto.EmailDto;

import javax.validation.Valid;

@Controller
public class PasswordRecoverController {

    @GetMapping("/forgot_password")
    public String passwordRecoverPage(){
        return "auth/passwordRecover";
    }

    @PostMapping("/recover_password")
    public String recoverPassword(@Valid EmailDto emailDto,
                                  BindingResult bindingResult,
                                  Model model){
        System.out.println();
        return "";
    }
}
