package com.example.TulgaBolamynTest.services;

import com.example.TulgaBolamynTest.domains.UDetails;
import com.example.TulgaBolamynTest.domains.User;

import java.util.List;

public interface UserService {

    void resetPassword(String email);

    List<User> getAll();

    UDetails getUDetailsById(Long id);

    UDetails update(UDetails uDetails);

    void changeRole(User user);

    void enableById(Long id);

    void blockById(Long id);

    void unblockById(Long id);

    User getUserById(Long id);
}
