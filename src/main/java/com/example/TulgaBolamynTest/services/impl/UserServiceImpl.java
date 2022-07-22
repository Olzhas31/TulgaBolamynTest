package com.example.TulgaBolamynTest.services.impl;

import com.example.TulgaBolamynTest.domains.UDetails;
import com.example.TulgaBolamynTest.domains.User;
import com.example.TulgaBolamynTest.repositories.UDetailsRepository;
import com.example.TulgaBolamynTest.repositories.UserRepository;
import com.example.TulgaBolamynTest.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UDetailsRepository uDetailsRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public UDetails getUDetailsById(Long id) {
        return uDetailsRepository.findById(id).get();
    }

    @Override
    public UDetails update(UDetails request) {
        UDetails uDetails = uDetailsRepository.findById(request.getId()).get();
        uDetails.setName(request.getName());
        uDetails.setSurname(request.getSurname());
        uDetails.setPhoneNumber(request.getPhoneNumber());
        uDetails.setEmail(request.getEmail());
        return uDetailsRepository.saveAndFlush(uDetails);
    }

    @Override
    public void changeRole(User request) {
        User user = userRepository.findById(request.getId()).get();
        user.setUserRole(request.getUserRole());
        userRepository.saveAndFlush(user);
    }

    @Override
    public void enableById(Long id) {
        userRepository.enableUser(id);
    }

    // TODO: бірден базаға запрос жасау керек
    @Override
    public void blockById(Long id) {
        User user = userRepository.findById(id).get();
        user.setLocked(true);
        userRepository.saveAndFlush(user);
    }

    // TODO: бірден базаға запрос жасау керек
    @Override
    public void unblockById(Long id) {
        User user = userRepository.findById(id).get();
        user.setLocked(false);
        userRepository.saveAndFlush(user);
    }

    // TODO:
    @Override
    public void resetPassword(String email) {

    }
}
