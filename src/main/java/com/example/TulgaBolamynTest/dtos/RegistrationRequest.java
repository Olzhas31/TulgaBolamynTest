package com.example.TulgaBolamynTest.dtos;

import com.example.TulgaBolamynTest.validation.PasswordMatches;
import com.example.TulgaBolamynTest.validation.ValidEmail;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
//@PasswordMatches
public class RegistrationRequest {

    @NotEmpty
    private String login;

//    @NotNull
//    @NotEmpty
    private String password;
    private String matchingPassword;

    private String name;
    private String surname;
    private String phoneNumber;

//    @ValidEmail
//    @NotNull
//    @NotEmpty
    private String email;

    public RegistrationRequest(){}
}
