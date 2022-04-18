package com.marting.store.entity.customValidations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExpDateValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpDate {

    String message() default "Credit Card is expired";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
