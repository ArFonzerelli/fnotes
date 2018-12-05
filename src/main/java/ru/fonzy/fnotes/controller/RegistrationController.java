package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.ProfileDto;
import ru.fonzy.fnotes.dto.UserDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.UserService;
import sun.plugin.util.UserProfile;

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
        return "/users/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            return "/users/register";
        }

        boolean result = userService.addUser(userDto);

        if (!result) {
            model.addAttribute("user_exists", "Такой логин уже существует");
            return "/users/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String openProfile(@AuthenticationPrincipal User currentUser,
                              @RequestParam long id, Model model){
        User user = userService.getUser(id);

        if (user == null || currentUser.getId() != id)
            return "redirect:/notes/all";

        ProfileDto profileDto = new ProfileDto(user.getId(), user.getUsername(), user.getEmail());

        model.addAttribute("userProfile", profileDto);

        return "/users/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@Valid ProfileDto profileDto,
                                BindingResult bindingResult,
                                Model model){
        System.out.println();
        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            model.addAttribute("userProfile", profileDto);
            System.out.println("error");

            return "/users/profile";
        }

        if (!userService.checkPassword(profileDto.getId(), profileDto.getOldPassword())){
            model.addAttribute("oldPasswordCorrect_failed", "Вы ввели неверный старый пароль");
            model.addAttribute("userProfile", profileDto);

            return "/users/profile";
        }

        userService.updateUserProfile(profileDto);

        return "redirect:/notes/all";

    }
}
