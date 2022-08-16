package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.services.BookService;
import com.example.TulgaBolamynTest.services.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/questions")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final BookService bookService;

    @GetMapping
    public String showQuestionsByBook(@RequestParam("id") Long bookId, Model model, Authentication authentication){
        log.info("Request to questions page by bookId={} : {} ", bookId, SecurityContextHolder.getContext().getAuthentication().getName());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("bookId", bookId);
        model.addAttribute("questions", questionService.getQuestionsByBookId(bookId));
        return "question/questions";
    }

    @GetMapping("/create")
    public String showCreateQuestionPage(Model model, @RequestParam("bookId") Long bookId, Authentication authentication){
        log.info("Request to create question page by bookId={} : {} ", bookId, SecurityContextHolder.getContext().getAuthentication().getName());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("question", new Question(bookService.getById(bookId)));
        return "question/newQuestion";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("question") Question question){
        log.info("Request to post question={} : {} ", question, SecurityContextHolder.getContext().getAuthentication().getName());
        questionService.create(question);
        return "redirect:/questions?id=" + question.getBook().getId();
    }

    @GetMapping("/edit")
    public String showEditQuestionPage(@RequestParam("id") Long id, Model model, Authentication authentication){
        log.info("Request to edit question page by id={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("question", questionService.getById(id));
        return "question/editQuestion";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("question") Question question){
        log.info("Request to update question={} : {} ", question, SecurityContextHolder.getContext().getAuthentication().getName());
        questionService.update(question);
        return "redirect:/questions?id=" + question.getBook().getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        log.info("Request to delete questionId={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        Long bookId = questionService.getById(id).getBook().getId();
        questionService.deleteById(id);
        return "redirect:/questions?id=" + bookId;
    }

}
