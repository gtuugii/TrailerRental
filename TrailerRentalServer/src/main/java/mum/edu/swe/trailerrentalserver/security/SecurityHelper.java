package mum.edu.swe.trailerrentalserver.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    public Long getCurrentUserId(){
       return ((JwtUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

}
