package com.example.TulgaBolamynTest.validation;

import com.example.TulgaBolamynTest.dtos.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final RegistrationRequest user = (RegistrationRequest) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}