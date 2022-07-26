package com.example.TulgaBolamynTest.services;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.dtos.TestCreationDTO;

import java.util.List;

public interface TestService {
    List<Book> getAll();

    TestCreationDTO testing(TestCreationDTO testForm);
}
