package com.example.TulgaBolamynTest.services.impl;

import com.example.TulgaBolamynTest.domains.UDetails;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.domains.UserRole;
import com.example.TulgaBolamynTest.dtos.RegistrationRequest;
import com.example.TulgaBolamynTest.exception_handling.UserAlreadyExistException;
import com.example.TulgaBolamynTest.repositories.UserRepository;
import com.example.TulgaBolamynTest.services.RegistrationService;
import com.example.TulgaBolamynTest.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(RegistrationRequest request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new UserAlreadyExistException("User with login " + request.getLogin() + " is already exist");
        }
        UDetails uDetails = new UDetails(
                request.getName(),
                request.getSurname(),
                request.getPhoneNumber(),
                request.getEmail(),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())
        );
        User user = new User(
                request.getLogin(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                uDetails
        );
        uDetails.setUser(user);
        userRepository.saveAndFlush(user);
    }

}
