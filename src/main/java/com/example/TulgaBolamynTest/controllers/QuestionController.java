package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{bookId}")
    public String showQuestionsByBook(@PathVariable Long bookId, Model model){
        List<Question> questions = questionService.getQuestionsByBookId(bookId);
        model.addAttribute("questions", questions);
        return "question/questions";
    }

    @GetMapping("/{bookId}/create")
    public String showCreateQuestionPage(@PathVariable Long bookId, @ModelAttribute("question") Question question){
        return "question/newQuestion";
    }

    @PostMapping("/{bookId}/create")
    public String create(@PathVariable Long bookId, @ModelAttribute("question") Question question){
        questionService.create(question, bookId);
        return "redirect:/questions/" + bookId;
    }

    @GetMapping("/{id}/edit")
    public String showEditQuestionPage(@PathVariable Long id, Model model){
        Question question = questionService.getById(id);
        model.addAttribute("question", question);
        return "question/newQuestion";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute("question") Question question){
        Question updatedQuestion = questionService.update(question);
        return "redirect:/questions/" + updatedQuestion.getBook().getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        questionService.deleteById(id);
        return "redirect:/questions";
    }

}
