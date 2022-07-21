package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String showBooksListPage(Model model){
        model.addAttribute("books", bookService.getAll());
        return "book/books";
    }

    @GetMapping("/create")
    public String showCreateNewBookPage(@ModelAttribute("book") Book book){
        return "book/newBook";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("book") Book book){
        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showEditBookPage(@PathVariable Long id, Model model){
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book/newBook";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("book") Book book){
        bookService.update(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/show")
    public String show(@PathVariable Long id){
        bookService.showBookById(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/hide")
    public String hide(@PathVariable Long id){
        bookService.hideBookById(id);
        return "redirect:/books";
    }

}
