package com.example.TulgaBolamynTest.services.impl;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.repositories.BookRepository;
import com.example.TulgaBolamynTest.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void create(Book book) {
        bookRepository.saveAndFlush(book);
    }

    @Override
    public void update(Book book) {
        bookRepository.saveAndFlush(book);
    }

    @Override
    public void showBookById(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setHidden(false);
        bookRepository.saveAndFlush(book);
    }

    @Override
    public void hideBookById(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setHidden(true);
        bookRepository.saveAndFlush(book);
    }
}
