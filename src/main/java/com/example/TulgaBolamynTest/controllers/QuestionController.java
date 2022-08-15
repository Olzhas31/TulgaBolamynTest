package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.services.BookService;
import com.example.TulgaBolamynTest.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final BookService bookService;

    @GetMapping
    public String showQuestionsByBook(@RequestParam("id") Long bookId, Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("bookId", bookId);
        model.addAttribute("questions", questionService.getQuestionsByBookId(bookId));
        return "question/questions";
    }

    @GetMapping("/create")
    public String showCreateQuestionPage(Model model, @RequestParam("bookId") Long bookId, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("question", new Question(bookService.getById(bookId)));
        return "question/newQuestion";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("question") Question question){
        questionService.create(question);
        return "redirect:/questions?id=" + question.getBook().getId();
    }

    @GetMapping("/edit")
    public String showEditQuestionPage(@RequestParam("id") Long id, Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("question", questionService.getById(id));
        return "question/editQuestion";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("question") Question question){
        questionService.update(question);
        return "redirect:/questions?id=" + question.getBook().getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        Long bookId = questionService.getById(id).getBook().getId();
        questionService.deleteById(id);
        return "redirect:/questions?id=" + bookId;
    }

}
