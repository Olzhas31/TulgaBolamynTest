package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.dtos.TestCreationDTO;
import com.example.TulgaBolamynTest.dtos.TestDTO;
import com.example.TulgaBolamynTest.services.QuestionService;
import com.example.TulgaBolamynTest.services.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainController {

    private final TestService testService;
    private final QuestionService questionService;

    // complete
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // complete
    @GetMapping("/tests")
    public String showTestsPage(Model model) {
        model.addAttribute("tests", testService.getAll());
        return "test/tests";
    }

    @GetMapping("/test")
    public String showTest(@RequestParam("id") Long id, Model model){
        TestCreationDTO testForm = new TestCreationDTO();
        for (Question question: questionService.getQuestionsByBookId(id)){
            testForm.addTest(new TestDTO(question));
        }
        model.addAttribute("testForm", testForm);
        return "test/test";
    }

    @PostMapping("/test")
    public String test(@ModelAttribute("testForm") TestCreationDTO testForm){
        for (TestDTO t: testForm.getTestList()){
            System.out.println(t);
        }
        return "redirect:/";
    }
}
