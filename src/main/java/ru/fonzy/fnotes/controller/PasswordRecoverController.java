package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.EmailDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.PasswordRecoverService;
import ru.fonzy.fnotes.service.UserService;

import javax.validation.Valid;

@Controller
public class PasswordRecoverController {

    private UserService userService;

    private PasswordRecoverService passwordRecoverService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordRecoverService(PasswordRecoverService passwordRecoverService) {
        this.passwordRecoverService = passwordRecoverService;
    }

    @GetMapping("/forgot_password")
    public String passwordRecoverPage(){
        return "auth/passwordRecover";
    }

    @PostMapping("/recover_password")
    public String recoverPassword(@Valid EmailDto emailDto,
                                  BindingResult bindingResult,
                                  Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);

            return "auth/passwordRecover";
        }

        User user = userService.getUser(emailDto.getEmail());

        if (user == null){
            model.addAttribute("email_not_found", "Пользователя с таким почтовым адресом не существует");

            return "auth/passwordRecover";
        }

        String passwordRecoverCode = passwordRecoverService.getPasswordRecoverCode();

        boolean passwordRecoverCodeSent = passwordRecoverService.sendPasswordRecoverEmail(user.getEmail(), user.getUsername(), passwordRecoverCode);

        if (!passwordRecoverCodeSent)
            return "redirect:/pass_recover_email_failed";

        return "redirect:/pass_recover_email_sent";

    }

    @GetMapping("/pass_recover_email_sent")
    public String emailConfirmPage(){
        return "auth/tech/passRecoverEmailSent";
    }

    @GetMapping("/pass_recover_email_failed")
    public String sendEmailFailedPage(){
        return "auth/tech/passRecoverEmailFailed";
    }
}
