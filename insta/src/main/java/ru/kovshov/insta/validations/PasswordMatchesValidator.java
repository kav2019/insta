package ru.kovshov.insta.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.kovshov.insta.annotations.PasswordMatches;
import ru.kovshov.insta.payload.request.SignupRequest;

import java.lang.annotation.Annotation;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SignupRequest userSignupRequest = (SignupRequest) value;
        return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
    }
}
