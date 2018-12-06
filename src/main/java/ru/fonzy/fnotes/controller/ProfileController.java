package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.PasswordDto;
import ru.fonzy.fnotes.dto.ProfileDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String openProfile(@AuthenticationPrincipal User currentUser,
                              @RequestParam long id, Model model){
        User user = userService.getUser(id);

        if (user == null || currentUser.getId() != id)
            return "redirect:/notes/all";

        ProfileDto profileDto = new ProfileDto(user.getId(), user.getUsername(), user.getEmail());

        model.addAttribute("user", profileDto);

        return "/profile/profilePage";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid ProfileDto profileDto,
                                BindingResult bindingResult,
                                Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            model.addAttribute("user", profileDto);

            return "/profile/profilePage";
        }

        userService.updateUserProfile(profileDto);

        return "redirect:/notes/all";

    }

    @GetMapping("change_password")
    public String changePasswordPage(@AuthenticationPrincipal User currentUser,
                                     @RequestParam long id, Model model){
        User user = userService.getUser(id);

        if (user == null || currentUser.getId() != id)
            return "redirect:/notes/all";

        PasswordDto passwordDto = new PasswordDto(id);

        model.addAttribute("user", passwordDto);

        return "/profile/changePassword";
    }

    @PostMapping("/change_password")
    public String changePassword(@Valid PasswordDto passwordDto,
                                 BindingResult bindingResult,
                                 Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            model.addAttribute("user", passwordDto);

            return "/profile/changePassword";
        }

        if (!userService.checkPassword(passwordDto.getId(), passwordDto.getOldPassword())){
            model.addAttribute("oldPasswordCorrect_failed", "Вы ввели неверный пароль");
            model.addAttribute("user", passwordDto);

            return "/profile/changePassword";
        }

        userService.updateUserPassword(passwordDto);

        return "redirect:/notes/all";

    }
}
