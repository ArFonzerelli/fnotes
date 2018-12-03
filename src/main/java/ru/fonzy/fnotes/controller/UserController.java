package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.UserDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.UserService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public String getAllUsers(Model model){
        Iterable<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);

        return "users/allUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam long id){
        boolean result = userService.deleteUserById(id);

        return "redirect:/users/all";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam long id, Model model){
        User user = userService.getUser(id);

        if (user == null)
            return "redirect:/users/all";

        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.isEnabled(), user.getRoles());

        model.addAttribute("user", userDto);
        model.addAttribute("roles", Role.values());

        return "users/editUser";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           @RequestParam Map<String, String> form,
                           Model model){

        System.out.println();

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);

            return "/users/editUser";
        }


        Role[] allRoles = Role.values();
        Set<Role> userRoles = new HashSet<>();

        for (Map.Entry<String, String> formParam : form.entrySet())
            for (Role role : allRoles)
                if (role.toString().equals(formParam.getKey()))
                    userRoles.add(role);

        userDto.setRoles(userRoles);

        userService.updateUser(userDto);

        return "redirect:/users/all";
    }



}
