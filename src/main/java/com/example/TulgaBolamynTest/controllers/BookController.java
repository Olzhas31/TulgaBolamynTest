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

    @GetMapping("/edit")
    public String showEditBookPage(@RequestParam("id") Long id, Model model){
        model.addAttribute("book", bookService.getById(id));
        return "book/editBook";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("book") Book book){
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") Long id){
        bookService.showBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/hide")
    public String hide(@RequestParam("id") Long id){
        bookService.hideBookById(id);
        return "redirect:/books";
    }

}
