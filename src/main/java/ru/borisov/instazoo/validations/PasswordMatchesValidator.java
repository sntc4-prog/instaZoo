package ru.borisov.instazoo.validations;

import ru.borisov.instazoo.annotations.PasswordMatches;
import ru.borisov.instazoo.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest userSignupRequest = (SignupRequest) obj;
        return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
    }

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }
}
