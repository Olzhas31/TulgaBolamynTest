package com.example.TulgaBolamynTest.services.impl;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.domains.Question;
import com.example.TulgaBolamynTest.repositories.BookRepository;
import com.example.TulgaBolamynTest.repositories.QuestionRepository;
import com.example.TulgaBolamynTest.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Question> getQuestionsByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        return questionRepository.findByBook(book);
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public void create(Question question) {
        questionRepository.saveAndFlush(question);
    }

    @Override
    public Question update(Question question) {
        return questionRepository.saveAndFlush(question);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
