package mum.edu.swe.trailerrentalserver.security;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface ConfSecured {

     String value();

}
