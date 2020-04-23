package com.ivan.CT.myAnnotation;

import com.ivan.CT.handlerAnnotations.CheckTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckTimeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTimeValid {
    String message() default "Invalid time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
