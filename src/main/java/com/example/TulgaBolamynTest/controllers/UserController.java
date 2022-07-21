package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.UDetails;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // complete
    @GetMapping
    public String showUserListPage(Model model){
        model.addAttribute("users", userService.getAll());
        return "user/users";
    }

    // complete
    @GetMapping("/{id}")
    public String showAccountPage(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUDetailsById(id));
        return "user/account";
    }

    @GetMapping("/edit")
    public String showEditPage(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUDetails());
        return "user/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") UDetails uDetails){
        UDetails updatedUDetails = userService.update(uDetails);
        return "redirect:/users/" + updatedUDetails.getUser().getId();
    }

    @GetMapping("/{id}/enable")
    public String enable(@PathVariable Long id){
        userService.enableById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/block")
    public String block(@PathVariable Long id){
        userService.blockById(id);
        return "redirect:/users";
    }

    @PostMapping("/{id}/unblock")
    public String unblock(@PathVariable Long id){
        userService.unblockById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/change-role")
    public String showChangeRolePage(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user/changeRole";
    }

    @PostMapping("/change-role")
    public String changeRole(@ModelAttribute("user") User user) {
        userService.changeRole(user);
        return "redirect:/users";
    }

}
