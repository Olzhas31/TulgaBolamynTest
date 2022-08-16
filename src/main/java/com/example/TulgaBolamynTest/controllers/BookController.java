package com.example.TulgaBolamynTest.controllers;

import com.example.TulgaBolamynTest.domains.Book;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String showBooksListPage(Model model, Authentication authentication){
        log.info("Request to get books page from : {} ", SecurityContextHolder.getContext().getAuthentication().getName());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("books", bookService.getAll());
        return "book/books";
    }

    @GetMapping("/create")
    public String showCreateNewBookPage(Model model, Authentication authentication){
        log.info("Request to get create book page from : {} ", SecurityContextHolder.getContext().getAuthentication().getName());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("book", new Book());
        return "book/newBook";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("book") Book book){
        log.info("Request to create book={} : {} ", book, SecurityContextHolder.getContext().getAuthentication().getName());
        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String showEditBookPage(@RequestParam("id") Long id, Model model, Authentication authentication){
        log.info("Request to edit book page by bookId={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUDetails().getSurname() + " " + user.getUDetails().getName());
        model.addAttribute("book", bookService.getById(id));
        return "book/editBook";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("book") Book book){
        log.info("Request to update book={} : {} ", book, SecurityContextHolder.getContext().getAuthentication().getName());
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") Long id){
        log.info("Request to show book by id={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        bookService.showBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/hide")
    public String hide(@RequestParam("id") Long id){
        log.info("Request to hide book by id={} : {} ", id, SecurityContextHolder.getContext().getAuthentication().getName());
        bookService.hideBookById(id);
        return "redirect:/books";
    }

}
