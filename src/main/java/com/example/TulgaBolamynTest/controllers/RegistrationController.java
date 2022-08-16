package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.dtos.RegistrationRequest;
import com.example.TulgaBolamynTest.exception_handling.AlreadyAuthorizedException;
import com.example.TulgaBolamynTest.services.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    // TODO: валидация жасау
    // TODO: exception
    @GetMapping("/registration")
    public String showRegistrationPage(@ModelAttribute("user") RegistrationRequest user, Principal principal){
        log.info("Request to registration page");
        if (principal != null){
            throw new AlreadyAuthorizedException("Вы уже авторизовались, сначала выйдите из системы");
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") // @Valid
                                RegistrationRequest user){
        log.info("Request to registration user={}", user);
        registrationService.register(user);
        return "redirect:/login";
    }
}
