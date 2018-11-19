package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model){
        Iterable<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);

        return "allUsers";
    }

    @GetMapping("/delete_user")
    public String deleteUser(@RequestParam long id){
        boolean result = userService.deleteUserById(id);
        return "redirect:/allUsers";
    }

    @GetMapping("/edit_user")
    public String editUser(@RequestParam long id, Model model){
        User user = userService.getUser(id);

        if (user == null)
            return "redirect:/allUsers";

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "editUser";
    }

    @PostMapping("/save_user")
    public String saveUser(@RequestParam Map<String, String> form){

        boolean enabled = false;

        if (form.containsKey("enabled"))
            enabled = true;

        Role[] allRoles = Role.values();
        Set<Role> userRoles = new HashSet<>();

        for (Map.Entry<String, String> formParam : form.entrySet())
            for (Role role : allRoles)
                if (role.toString().equals(formParam.getKey()))
                    userRoles.add(role);

        userService.updateUser(form.get("id"), form.get("username"), form.get("password"), enabled, userRoles);

        return "redirect:/allUsers";
    }



}
