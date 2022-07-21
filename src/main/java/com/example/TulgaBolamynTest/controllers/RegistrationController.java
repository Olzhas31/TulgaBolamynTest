package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.dtos.RegistrationRequest;
import com.example.TulgaBolamynTest.exception_handling.AlreadyAuthorizedException;
import com.example.TulgaBolamynTest.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // TODO: валидация жасау
    @GetMapping("/registration")
    public String showRegistrationPage(@ModelAttribute("user") RegistrationRequest user, Principal principal){
        if (principal != null){
            throw new AlreadyAuthorizedException("Вы уже авторизовались, сначала выйдите из системы");
        }
        return "registration";
    }

    // complete
    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") // @Valid
                                RegistrationRequest user){
        registrationService.register(user);
        return "redirect:/login";
    }
}
