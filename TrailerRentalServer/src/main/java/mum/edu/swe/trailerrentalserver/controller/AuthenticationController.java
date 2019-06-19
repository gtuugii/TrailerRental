package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.Login;
import mum.edu.swe.trailerrentalserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String login(@RequestBody Login login) {
        return authenticationService.login(login);
    }

    @DeleteMapping
    public boolean login(@RequestParam String token)
    {
        return authenticationService.logOut();
    }

    @GetMapping
    public List<String> getAuthorities(){
        return authenticationService.getAuthorities();
    }

}
