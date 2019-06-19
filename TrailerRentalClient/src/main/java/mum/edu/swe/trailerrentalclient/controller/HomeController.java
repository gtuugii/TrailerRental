package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.config.TokenHelper;
import mum.edu.swe.trailerrentalclient.model.DB;
import mum.edu.swe.trailerrentalclient.model.Dashboard;
import mum.edu.swe.trailerrentalclient.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private String api_url = Config.URL;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    DB status;

    @GetMapping(value={"/", "/home"})
    public String list(Model model){
        Dashboard r = new Dashboard();
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Dashboard> response = restTemplate.exchange(api_url + "dashboard", HttpMethod.GET, entity, Dashboard.class);

            r = response.getBody();
            System.out.println("response: " + r);
            System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            model.addAttribute("dashboard", r);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("dashboard", r);
        }

        return "index";
    }

}
