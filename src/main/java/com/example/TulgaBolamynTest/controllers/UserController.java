package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.UDetails;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.domains.UserRole;
import com.example.TulgaBolamynTest.exception_handling.MyException;
import com.example.TulgaBolamynTest.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showUserListPage(Model model){
        log.info("Request to users list page from : {} ", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("users", userService.getAll());
        return "user/users";
    }

    // TODO: exception ды өзгерту
    @GetMapping("/account")
    public String showAccountPage(@RequestParam(value = "id", required = false) Long id, Model model, Authentication authentication){
        log.info("Request to profile page id={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
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
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        return "user/account";
    }

    @GetMapping("/edit")
    public String showEditPage(Authentication authentication, Model model){
        log.info("Request to edit profile page : {} ", SecurityContextHolder.getContext().getAuthentication().getName());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUDetails());
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        return "user/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") UDetails uDetails){
        log.info("Request to update user user={} : {} ", uDetails, SecurityContextHolder.getContext().getAuthentication().getName());
        userService.update(uDetails);
        return "redirect:/logout";
    }

    @GetMapping("/enable")
    public String enable(@RequestParam("id") Long id){
        log.info("Request to enable user by id={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        userService.enableById(id);
        return "redirect:/users";
    }

    @GetMapping("/block")
    public String block(@RequestParam("id") Long id){
        log.info("Request to block user by id={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        userService.blockById(id);
        return "redirect:/users";
    }

    @GetMapping("/unblock")
    public String unblock(@RequestParam("id") Long id){
        log.info("Request to unblock user by id={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        userService.unblockById(id);
        return "redirect:/users";
    }

    //  TODO:
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
