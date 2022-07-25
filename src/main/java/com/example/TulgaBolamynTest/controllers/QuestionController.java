package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.services.BookService;
import com.example.TulgaBolamynTest.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final BookService bookService;

    // complete
    @GetMapping
    public String showQuestionsByBook(@RequestParam("id") Long bookId, Model model){
        model.addAttribute("bookId", bookId);
        model.addAttribute("questions", questionService.getQuestionsByBookId(bookId));
        return "question/questions";
    }

    // complete
    @GetMapping("/create")
    public String showCreateQuestionPage(Model model, @RequestParam("bookId") Long bookId){
        model.addAttribute("question", new Question(bookService.getById(bookId)));
        return "question/newQuestion";
    }

    // complete
    @PostMapping("/create")
    public String create(@ModelAttribute("question") Question question){
        questionService.create(question);
        return "redirect:/questions?id=" + question.getBook().getId();
    }

    // complete
    @GetMapping("/edit")
    public String showEditQuestionPage(@RequestParam("id") Long id, Model model){
        model.addAttribute("question", questionService.getById(id));
        return "question/editQuestion";
    }

    // complete
    @PostMapping("/edit")
    public String update(@ModelAttribute("question") Question question){
        questionService.update(question);
        return "redirect:/questions?id=" + question.getBook().getId();
    }

    // complete
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        Long bookId = questionService.getById(id).getBook().getId();
        questionService.deleteById(id);
        return "redirect:/questions?id=" + bookId;
    }

}
