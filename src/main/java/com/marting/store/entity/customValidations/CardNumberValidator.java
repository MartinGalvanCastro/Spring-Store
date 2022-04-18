package com.marting.store.entity.customValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CardNumberValidator implements ConstraintValidator<CardNumber, String> {


    @Override
    public void initialize(CardNumber theCardNumber) {
        ConstraintValidator.super.initialize(theCardNumber);
    }

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;

        if (Objects.nonNull(cardNumber) && !cardNumber.isBlank()) {

            int digits = cardNumber.length();
            int sum = 0;
            boolean isSecond = false;

            for (int i = digits - 1; i >= 0; i--) {
                int d = cardNumber.charAt(i) - '0';
                if (isSecond) {
                    d *= 2;
                }
                sum += d / 10;
                sum += d % 10;

                isSecond = !isSecond;
            }
            result = (sum % 10 == 0);
        }
        return result;
    }
}
