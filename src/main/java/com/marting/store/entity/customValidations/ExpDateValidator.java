package com.marting.store.entity.customValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class ExpDateValidator implements ConstraintValidator<ExpDate,String> {
    @Override
    public void initialize(ExpDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String ExpDate, ConstraintValidatorContext constraintValidatorContext) {
        String[] date = ExpDate.split("-");
        if(ExpDate.isBlank() || Objects.isNull(ExpDate)) return false;

        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[0]);
        Date ExpDateComparation = new GregorianCalendar(year,month-1,1).getTime();
        Date nowDate = new Date();
        return ExpDateComparation.after(nowDate);
    }
}
