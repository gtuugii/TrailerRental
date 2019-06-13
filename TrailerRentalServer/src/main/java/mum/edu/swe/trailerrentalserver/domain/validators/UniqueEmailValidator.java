package mum.edu.swe.trailerrentalserver.domain.validators;

import mum.edu.swe.trailerrentalserver.domain.User;
import mum.edu.swe.trailerrentalserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail arg0) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = null;
        try {
            user = userService.findUsersByEmail(value).get(0);
        }
        catch (Exception e) {
            System.out.println("Couldn't find user...");
        }

        return user == null ? true : false;
    }

}
