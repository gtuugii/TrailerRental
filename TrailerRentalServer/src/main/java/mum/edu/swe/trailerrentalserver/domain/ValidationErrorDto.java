package mum.edu.swe.trailerrentalserver.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationErrorDto {
    private String errorType;
    private List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public void addError(String path, String message) {
        FieldErrorDto error = new FieldErrorDto(path, message);
        fieldErrors.add(error);
    }

}
