package mum.edu.swe.trailerrentalclient.controller;

import mum.edu.swe.trailerrentalclient.config.Config;
import mum.edu.swe.trailerrentalclient.model.DB;
import mum.edu.swe.trailerrentalclient.model.Rent;
import mum.edu.swe.trailerrentalclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rents")
public class RentController {

    private String api_url = Config.URL;

    @Autowired
    DB status;

    @GetMapping("/list")
    public String list(Model model){
        try{
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<Rent[]> entity = new HttpEntity<Rent[]>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Rent[]> response = restTemplate.exchange(api_url + "rents", HttpMethod.GET, entity, Rent[].class);
            final List<Rent> rents = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("rents", rents);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("list - rents");
        return "rents/rent-list";
    }

}
