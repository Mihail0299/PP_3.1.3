package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String helloPage(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("admin", userService.findUserByEmail(principal.getName()));
        modelMap.addAttribute("allUsers", userService.findAll());
        modelMap.addAttribute("newUser",new User());
        return "list";
    }



    @PostMapping()
    public String registration(@ModelAttribute("newUser") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "list";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping ("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PatchMapping ("/update/{id}")
    public String updateUser(@ModelAttribute("userEd") User user, @PathVariable(name = "id") Long id, ModelMap model) {
        model.addAttribute("user",userService.findUserById(id));
        userService.save(user);
        return "redirect:/admin";
    }
}
