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
import ru.fonzy.fnotes.dto.UserDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.UserService;

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

        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.isEnabled(), user.getRoles());

        model.addAttribute("user", userDto);

        return "/users/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(){
        return "";
    }
}
