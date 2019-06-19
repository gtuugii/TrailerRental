package mum.edu.swe.trailerrentalserver.security;

import mum.edu.swe.trailerrentalserver.exceptions.ConfAuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {

    }


    @Override
    public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        JwtUserDetails jwtUserDetailsRes = jwtUtil.decode(token);
        if (jwtUserDetailsRes == null) {
            throw new ConfAuthorizationException("ad-----------------------");
        }

        String csvRoles = String.join(",", jwtUserDetailsRes.getRoles());
        List<GrantedAuthority> grantedAuthorities =AuthorityUtils.commaSeparatedStringToAuthorityList(csvRoles);
        jwtUserDetailsRes.setAuthorities(grantedAuthorities);


        return jwtUserDetailsRes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
