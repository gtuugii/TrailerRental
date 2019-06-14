package mum.edu.swe.trailerrentalserver.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention( RetentionPolicy.RUNTIME )
@Constraint( validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "{user.email.exist.message}";
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}
