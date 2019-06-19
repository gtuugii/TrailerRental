package mum.edu.swe.trailerrentalserver.service.impl;

import mum.edu.swe.trailerrentalserver.domain.Login;
import mum.edu.swe.trailerrentalserver.domain.User;
import mum.edu.swe.trailerrentalserver.repository.AuthenticationRepository;
import mum.edu.swe.trailerrentalserver.security.JwtAuthenticationTokenFilter;
import mum.edu.swe.trailerrentalserver.security.JwtUserDetails;
import mum.edu.swe.trailerrentalserver.security.JwtUtil;
import mum.edu.swe.trailerrentalserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationRepository authenticationRepo;
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepo, JwtUtil jwtUtil) {
        this.authenticationRepo = authenticationRepo;
        this.jwtUtil = jwtUtil;
    }

    public boolean logOut() {
        SecurityContextHolder.clearContext();
        return true;
    }

    public List<String> getAuthorities() {

        String header = httpServletRequest.getHeader("Authorization");
        if (header != null || header.startsWith("Bearer ")) {
            String token = header.split(" ")[1];
             JwtUserDetails jwtUserDetails = jwtUtil.decode(token);
            String csvRoles = String.join(",", jwtUserDetails.getRoles());
            //List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(csvRoles);
            return jwtUserDetails.getRoles();
        }
        return null;
    }

    @Override
    public String login(Login login) {
        User credential = authenticationRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());

        if (credential != null) {
            // generate token
            JwtUserDetails jwtUserDetails = new JwtUserDetails();
            jwtUserDetails.setUsername(login.getEmail());

            //fetch roles
            jwtUserDetails.setRoles(credential.getRoles().stream().map(l -> l.getRole()).collect(Collectors.toList()));

            return jwtUtil.encode(jwtUserDetails);
        }

        return null;
    }
}
