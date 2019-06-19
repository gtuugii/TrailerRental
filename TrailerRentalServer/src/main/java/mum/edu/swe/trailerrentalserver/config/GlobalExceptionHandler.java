package mum.edu.swe.trailerrentalserver.config;

import mum.edu.swe.trailerrentalserver.domain.ValidationErrorDto;
import mum.edu.swe.trailerrentalserver.exceptions.ConfAuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ValidationErrorDto validationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return convertError(fieldErrors);

    }

    @ExceptionHandler({ConfAuthorizationException.class})
    public String denied(Exception e) {
        return "403";
    }


    @ExceptionHandler({Exception.class})
    public String doIt(Exception e) {
        e.printStackTrace();
        return "redirect:/authorization/login";
    }

    private ValidationErrorDto convertError(List<FieldError> fieldErrors) {
        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setErrorType("ValidationError");

        for(FieldError fieldError : fieldErrors){
            validationErrorDto.addError(fieldError.getField(),messageSourceAccessor.getMessage(fieldError));
        }

        return validationErrorDto;

    }

}
