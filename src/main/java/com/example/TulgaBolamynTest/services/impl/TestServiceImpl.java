package com.example.TulgaBolamynTest.services.impl;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.dtos.TestCreationDTO;
import com.example.TulgaBolamynTest.dtos.TestDTO;
import com.example.TulgaBolamynTest.repositories.BookRepository;
import com.example.TulgaBolamynTest.repositories.QuestionRepository;
import com.example.TulgaBolamynTest.services.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final BookRepository bookRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll()
                .stream()
                .filter(book -> !book.getHidden())
                .collect(Collectors.toList());
    }

    @Override
    public TestCreationDTO testing(TestCreationDTO testForm) {
        int count = 0;
        for (TestDTO testDTO : testForm.getTestList()){
            if (testDTO.getSelectedOption()==null) {
                testDTO.setCorrect(false);
                continue;
            }
            switch (testDTO.getSelectedOption()) {
                case "0": testDTO.setSelectedOption(testDTO.getOption0()); break;
                case "1": testDTO.setSelectedOption(testDTO.getOption1()); break;
                case "2": testDTO.setSelectedOption(testDTO.getOption2()); break;
                case "3": testDTO.setSelectedOption(testDTO.getOption3()); break;
                default: testDTO.setSelectedOption("-");
            }
            Question question = questionRepository.findById(testDTO.getId()).get();
            if (testDTO.getSelectedOption().equals(question.getOption0())){
                count++;
                testDTO.setCorrect(true);
            } else {
                testDTO.setCorrect(false);
            }
        }
        testForm.setPoint(count);
        return testForm;
    }
}
