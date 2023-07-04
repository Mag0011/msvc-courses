package org.company.springcloud.msvc.courses.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RequestValidationService {

    public Map<String, String> validateRequest(List<FieldError> errors){
        return errors.stream().collect(Collectors.toMap(FieldError::getField,
                err -> (err.getDefaultMessage() == null) ? "Field: " + err.getField() + "not valid" : err.getDefaultMessage(),
                (a, b) -> a));
    }

}
