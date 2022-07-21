package com.example.TulgaBolamynTest.services;

import com.example.TulgaBolamynTest.domains.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getById(Long id);

    void create(Book book);

    void update(Book book);

    void showBookById(Long id);

    void hideBookById(Long id);
}
