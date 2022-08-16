package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.dtos.TestCreationDTO;
import com.example.TulgaBolamynTest.dtos.TestDTO;
import com.example.TulgaBolamynTest.services.QuestionService;
import com.example.TulgaBolamynTest.services.ResultService;
import com.example.TulgaBolamynTest.services.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
public class MainController {

    private final TestService testService;
    private final QuestionService questionService;
    private final ResultService resultService;

    @GetMapping("/")
    public String index(Model model, Authentication authentication){
        log.info("Request to main page from : {} ", SecurityContextHolder.getContext().getAuthentication().getName());
        if (authentication != null){
            User user = (User) authentication.getPrincipal();
            model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        }
        return "index";
    }

    @GetMapping("/tests")
    public String showTestsPage(Model model) {
        log.info("Request to tests page from : {} ", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("tests", testService.getAll());
        return "test/tests";
    }

    @GetMapping("/test")
    public String showTest(@RequestParam("id") Long id, Model model){
        log.info("Request to test page of bookId={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        TestCreationDTO testForm = new TestCreationDTO();
        testForm.setBookId(id);
        for (Question question: questionService.getQuestionsByBookId(id)){
            testForm.addTest(new TestDTO(question));
        }
        model.addAttribute("testForm", testForm);
        return "test/test";
    }

    @PostMapping("/test")
    public String test(@ModelAttribute("testForm") TestCreationDTO testForm, Model model, Authentication authentication){
        log.info("Request to test result page of bookId={} : {} ", testForm.getBookId(), SecurityContextHolder.getContext().getAuthentication().getName());
        TestCreationDTO testCreationDTO = testService.testing(testForm);
        resultService.save(testCreationDTO, (User) authentication.getPrincipal());
        model.addAttribute("results", testCreationDTO);
        return "test/resultsPage";
    }
}
