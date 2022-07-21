package com.example.TulgaBolamynTest.services;

import com.example.TulgaBolamynTest.domains.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestionsByBookId(Long bookId);

    Question getById(Long id);

    void create(Question question, Long bookId);

    Question update(Question question);

    void deleteById(Long id);
}
