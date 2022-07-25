package com.example.TulgaBolamynTest.services.impl;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.repositories.BookRepository;
import com.example.TulgaBolamynTest.services.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll()
                .stream()
                .filter(book -> !book.getHidden())
                .collect(Collectors.toList());
    }
}
