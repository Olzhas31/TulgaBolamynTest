package com.example.TulgaBolamynTest.services.impl;

import com.example.TulgaBolamynTest.domains.Result;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.dtos.TestCreationDTO;
import com.example.TulgaBolamynTest.repositories.BookRepository;
import com.example.TulgaBolamynTest.repositories.ResultRepository;
import com.example.TulgaBolamynTest.services.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final BookRepository bookRepository;

    @Override
    public void save(TestCreationDTO testCreationDTO, User user) {
        Result result = new Result();
        result.setPoint20(testCreationDTO.getPoint());
        result.setUser(user);
        result.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        result.setBook(bookRepository.findById(testCreationDTO.getBookId()).get());
        resultRepository.saveAndFlush(result);
    }
}
