package com.example.TulgaBolamynTest.services;

import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.dtos.TestCreationDTO;

public interface ResultService {
    void save(TestCreationDTO testCreationDTO, User user);
}
