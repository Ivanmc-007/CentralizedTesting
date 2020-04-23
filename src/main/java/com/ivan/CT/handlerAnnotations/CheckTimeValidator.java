package com.ivan.CT.handlerAnnotations;

import com.ivan.CT.helper.MyDateParser;
import com.ivan.CT.myAnnotation.MyTimeValid;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.util.Objects;

public class CheckTimeValidator implements ConstraintValidator<MyTimeValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value))
            return false;
        try {
            MyDateParser.convertStringAsJustTimeToDate(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
