package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserD;
import ru.kata.spring.boot_security.demo.services.UDetailsService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/user")
public class userController {

    private final UDetailsService userService;

    @Autowired
    public userController(UDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public String showUser(Model model, Principal principal) {
        UserD userD = (UserD)((Authentication) principal).getPrincipal();
        List<User> user = Collections.singletonList(userService.getUser(userD.getId()));
        model.addAttribute("user", user);
        return "/user/user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/user/user-info2";
        }
        userService.updateUser(user);
        return "redirect:/user/info";

    }

    @GetMapping("/updateInfo")
    public String initUserForm(@RequestParam("userId") int id, Model model) {

        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "/user/user-info2";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);

        return "redirect:/login";
    }
}
