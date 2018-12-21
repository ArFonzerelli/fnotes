package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.EmailDto;
import ru.fonzy.fnotes.dto.PasswordDto;
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

    @PostMapping("/send_recover_email")
    public String sendRecoverEmail(@Valid EmailDto emailDto,
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

        String passwordRecoverCode = passwordRecoverService.getUniqueCode();
        userService.addPasswordRecoverCode(user, passwordRecoverCode);

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

    @GetMapping("/recover/{recoverCode}")
    public String recoverPassword(@PathVariable String recoverCode, Model model){
        User user = passwordRecoverService.getUserByCode(recoverCode);

        if (user == null){
            model.addAttribute("error_msg", "Данный код восcтановления пароля не найден.");
            return "auth/login";
        }

        model.addAttribute("user_id", user.getId());

        return "auth/newPassword";
    }

    @PostMapping("/recover_password")
    public String recoverPasswordPage(@Valid PasswordDto passwordDto,
                                    BindingResult bindingResult,
                                    Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            model.addAttribute("user", passwordDto);

            return "/recover_password";
        }

        userService.updateUserPassword(passwordDto);

        return "redirect:/password_changed";
    }

    @GetMapping("/password_changed")
    public String passwordChanged(){
        return "auth/tech/passwordChanged";
    }
}
