package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.UDetails;
import com.example.TulgaBolamynTest.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    // complete
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    // TODO:
    @GetMapping("/reset-password")
    public String showForgetPasswordPage(@ModelAttribute("email") UDetails uDetails){
        return "resetPassword";
    }

    // TODO:
    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("email") UDetails uDetails){
        userService.resetPassword(uDetails.getEmail());
        return "redirect:/login";
    }
}
