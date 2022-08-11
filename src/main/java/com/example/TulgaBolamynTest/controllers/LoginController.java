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

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/password-recovery")
    public String showForgetPasswordPage(@ModelAttribute("user") UDetails uDetails){
        return "password-recovery";
    }

    // TODO:
    @PostMapping("/password-recovery")
    public String resetPassword(@ModelAttribute("user") UDetails uDetails){
        System.out.println(uDetails.getEmail());
//        userService.resetPassword(uDetails.getEmail());
        return "redirect:/login";
    }
}
