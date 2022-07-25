package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.UDetails;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.domains.UserRole;
import com.example.TulgaBolamynTest.exception_handling.MyException;
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

    // TODO: exception ды өзгерту
    @GetMapping("/account")
    public String showAccountPage(@RequestParam(value = "id", required = false) Long id, Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (id == null || id == user.getId()) {
            model.addAttribute("login", user.getLogin());
            model.addAttribute("user", user.getUDetails());
        } else if (user.getUserRole() == UserRole.ADMIN) {
            model.addAttribute("login", userService.getUserById(id).getLogin());
            model.addAttribute("user", userService.getUDetailsById(id));
        } else {
            throw new MyException("Доступ жоқ");
        }
        return "user/account";
    }

    // complete
    @GetMapping("/edit")
    public String showEditPage(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUDetails());
        return "user/edit";
    }

    // complete
    @PostMapping("/edit")
    public String update(@ModelAttribute("user") UDetails uDetails){
        userService.update(uDetails);
        return "redirect:/logout";
    }

    // complete
    @GetMapping("/enable")
    public String enable(@RequestParam("id") Long id){
        userService.enableById(id);
        return "redirect:/users";
    }

    // complete
    @GetMapping("/block")
    public String block(@RequestParam("id") Long id){
        userService.blockById(id);
        return "redirect:/users";
    }

    // complete
    @GetMapping("/unblock")
    public String unblock(@RequestParam("id") Long id){
        userService.unblockById(id);
        return "redirect:/users";
    }

    // TODO:
    @GetMapping("/{id}/change-role")
    public String showChangeRolePage(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user/changeRole";
    }

    // TODO:
    @PostMapping("/change-role")
    public String changeRole(@ModelAttribute("user") User user) {
        userService.changeRole(user);
        return "redirect:/users";
    }

}
