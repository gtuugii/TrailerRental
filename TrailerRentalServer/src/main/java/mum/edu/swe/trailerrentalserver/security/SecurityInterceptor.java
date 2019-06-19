package mum.edu.swe.trailerrentalserver.security;

import lombok.var;
import mum.edu.swe.trailerrentalserver.exceptions.ConfAuthorizationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class SecurityInterceptor {

    @Pointcut("@annotation(mum.edu.swe.trailerrentalserver.security.ConfSecured)")
    public void annotation() {
    }

    @Before("annotation()")
    public void doAuth(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        ConfSecured myAnnotation = method.getAnnotation(ConfSecured.class);
        String value = myAnnotation.value();

        var roles = ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles();

        var result = roles.stream().filter(l -> l.equals(value)).findAny();
        if (!result.isPresent() ||result == null || result.get() == null) {
            throw new ConfAuthorizationException("403");
        }

    }

}
