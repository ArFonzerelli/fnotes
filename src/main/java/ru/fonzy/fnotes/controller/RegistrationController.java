package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.fonzy.fnotes.dto.CaptchaResponseDto;
import ru.fonzy.fnotes.dto.UserDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.ActivationService;
import ru.fonzy.fnotes.service.UserService;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    private ActivationService activationService;

    private RestTemplate restTemplate;

    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setActivationService(ActivationService activationService) {
        this.activationService = activationService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/register")
    public String registration(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           @RequestParam("g-recaptcha-response") String captchaResponse,
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

        String googleCaptchaUrl = String.format(CAPTCHA_URL, recaptchaSecret, captchaResponse);

        CaptchaResponseDto response = restTemplate.postForObject(googleCaptchaUrl, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()){
            model.addAttribute("captcha_failed", "Вы не отметили капчу");

            return "auth/register";
        }

        String activationCode = activationService.getUniqueCode();
        userService.addUserRegistered(userDto, activationCode);

        boolean activationEmailSent = activationService.sendActivationEmail(userDto.getEmail(), userDto.getUsername(), activationCode);

        if (!activationEmailSent)
            return "redirect:/register_email_failed";

        return "redirect:/register_email_sent";
    }

    @GetMapping("/register_email_sent")
    public String emailConfirmPage(){
        return "auth/tech/registerEmailSent";
    }

    @GetMapping("/register_email_failed")
    public String sendEmailFailedPage(){
        return "auth/tech/registerEmailFailed";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model){
        boolean isActivated = activationService.activateUserByActivationCode(code);

        if (isActivated)
            model.addAttribute("ok_msg", "Вы успешно подтвердили свой Email. Пожалуйста, авторизуйтесь.");

        else
            model.addAttribute("error_msg", "Данный код активации не найден.");

        return "auth/login";
    }

}
