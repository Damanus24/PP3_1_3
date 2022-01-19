package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UDetailsService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    private final UDetailsService userService;

    @Autowired
    public adminController(UDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        return "admin/all-users";
    }

    @GetMapping("/addNewUser")
    public String getUserForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "admin/user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/user-info";
        }
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/admin/user-info2";
        }
        userService.updateUser(user);
        return "redirect:/admin/users";

    }

    @GetMapping("/updateInfo")
    public String initUserForm(@RequestParam("userId") int id, Model model) {

        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "/admin/user-info2";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);

        return "redirect:/admin/users";
    }

}