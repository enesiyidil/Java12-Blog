package com.enes.utility;

import com.enes.annotation.ValidPassword;
import org.passay.*;
import org.passay.PasswordValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
