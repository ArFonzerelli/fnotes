package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fonzy.fnotes.dto.UserDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.UserService;
import java.util.Map;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String registration(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            return "auth/register";
        }

        Map<String, String> userExistsErrors = userService.checkUserExists(userDto);

        if (userExistsErrors.size() != 0){
            for (Map.Entry<String, String> entry : userExistsErrors.entrySet())
                model.addAttribute(entry.getKey(), entry.getValue());

            return "auth/register";
        }

        try {
            userService.addUser(userDto);
        }
        catch (MailSendException e){
            e.printStackTrace();
            return "redirect:/send_email_failed";
        }

        return "redirect:/email_confirm";
    }

    @GetMapping("/email_confirm")
    public String emailConfirmPage(){
        return "auth/email_confirm";
    }

    @GetMapping("/send_email_failed")
    public String sendEmailFailedPage(){
        return "auth/send_email_failed";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model){
        boolean isActivated = userService.activateUser(code);

        if (isActivated)
            model.addAttribute("ok_msg", "Вы успешно подтвердили свой Email. Пожалуйста, авторизуйтесь.");

        else
            model.addAttribute("error_msg", "Данный код активации не найден.");

        return "auth/login";
    }

}
