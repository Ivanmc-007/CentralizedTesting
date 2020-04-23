package com.ivan.CT.helper;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface Converter {
    static <T> Set<T>  convertListToSet(List<T> list) {
        return new HashSet<>(list);
    }

    static Map<String,String> convertBindingResultToMap(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(fieldError -> fieldError.getField()+"Error", FieldError::getDefaultMessage));
    }
}
