package mum.edu.swe.trailerrentalserver.service;

import mum.edu.swe.trailerrentalserver.domain.Login;

import java.util.List;

public interface AuthenticationService {
    String login(Login login);
    boolean logOut();
    List<String> getAuthorities();
}
