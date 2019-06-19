package mum.edu.swe.trailerrentalclient.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value={"/", "/home"})
    public String home(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return "index";
    }

}
