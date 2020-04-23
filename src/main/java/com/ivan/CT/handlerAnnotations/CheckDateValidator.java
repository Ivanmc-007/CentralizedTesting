package com.ivan.CT.handlerAnnotations;

import com.ivan.CT.helper.MyDateParser;
import com.ivan.CT.myAnnotation.MyDateValid;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;

public class CheckDateValidator implements ConstraintValidator<MyDateValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value))
            return false;
        try {
            MyDateParser.convertStringAsJustDateToDate(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
